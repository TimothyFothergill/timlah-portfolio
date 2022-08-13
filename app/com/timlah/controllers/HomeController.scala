package com.timlah.controllers

import com.timlah.models.{Author, ContactData, EnquiryType}
import play.api.mvc._
import com.timlah.services.{BlogService, CurrentProjects, EmailService}

import javax.inject._

@Singleton
class HomeController @Inject()(
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
      val enquiryTypes = EnquiryType.values
      Ok(com.timlah.views.html.contact(boundForm, enquiryTypes.map(_.asString)))
    }
  }

  def latestBlog() = Action { implicit request: Request[AnyContent] =>
    Ok(com.timlah.views.html.blog(blogService.createBlogPost(Author(4, "Timlah", Seq()), None, "New 3D RPG Prototype available in Projects")))
  }

  def contactSubmit() = Action { implicit request: MessagesRequest[AnyContent] => {
    val boundForm = ContactData.contactForm.bindFromRequest()
    val enquiryTypes = EnquiryType.values
    boundForm.fold(
      formWithErrors => {
        BadRequest(com.timlah.views.html.contact(formWithErrors, enquiryTypes.map(_.asString)))
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