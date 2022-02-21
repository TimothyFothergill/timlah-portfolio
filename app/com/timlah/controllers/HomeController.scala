package com.timlah.controllers

import akka.http.scaladsl.model.DateTime
import com.timlah.models.{Author, BlogPost, ContactData}
import com.timlah.repositories.BlogPostRepository
import play.api.libs.json.Json
import play.api.mvc._
import com.timlah.services.{BlogService, CurrentProjects, EmailService}

import javax.inject._

@Singleton
class HomeController @Inject()(
  blogPostRepository  : BlogPostRepository,
  blogService         : BlogService,
  cc                  : MessagesControllerComponents,
  currentProjects     : CurrentProjects,
  emailService        : EmailService
) extends MessagesAbstractController(cc) {

    def index() = Action { implicit request: Request[AnyContent] =>
      Ok(com.timlah.views.html.index())
    }

    def about() = Action { implicit request: Request[AnyContent] =>
      Ok(com.timlah.views.html.about())
    }

    def projects() = Action { implicit request: Request[AnyContent] => {
      Ok(com.timlah.views.html.projects(currentProjects.currentProjects(), currentProjects.closedProjects()))
    }
  }
  def contactPage() = Action { implicit request: MessagesRequest[AnyContent] => {
      val boundForm = ContactData.contactForm
      // Convert enquiryTypes to a sealed trait
      val enquiryTypes: Seq[String] = Seq("Business Enquiry", "Feedback", "Other")
      Ok(com.timlah.views.html.contact(boundForm, enquiryTypes))
    }
  }

  def testJson() = Action { implicit request: Request[AnyContent] =>
    blogPostRepository.mongoConnection()
    Ok(Json.toJson((blogService.createBlogPost(Author(1, "Timlah", Seq()), None, "This is my title", "This is a test blog post... trying newlines, too?"))))
  }

  def latestBlog() = Action { implicit request: Request[AnyContent] =>
    Ok(com.timlah.views.html.blog(blogService.createBlogPost(Author(1, "Timlah", Seq()), None, "This is my title", "This is a test blog post... trying newlines, too?")))
  }

  def contactSubmit() = Action { implicit request: MessagesRequest[AnyContent] => {
    val boundForm = ContactData.contactForm.bindFromRequest()
    val enquiryTypes: Seq[String] = Seq("Business Enquiry", "Feedback", "Other")
    boundForm.fold(
      formWithErrors => {
        BadRequest(com.timlah.views.html.contact(formWithErrors, enquiryTypes))
      },
      submittedData => {
        val data = ContactData(submittedData.name, submittedData.email, submittedData.subject, submittedData.enquiry, submittedData.contents)
        emailService.sendEmail(
            subject = data.subject match {
              case Some(data) => data
              case None => "Enquiry from timlah.com"
            },
            name = data.name,
            address = data.email match {
              case Some(data) => data
              case None => "me@timlah.com"
            },
          enquiry = submittedData.enquiry,
          content = data.contents
        )
        Redirect(routes.HomeController.index()).flashing("success" -> "Contact message sent, thank you.")
      }
    )
    }
  }
}