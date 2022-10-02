package com.timlah.controllers

import com.timlah.models.{Author, ContactData, EnquiryType}
import com.timlah.repositories.BlogPostRepository
import play.api.mvc._
import com.timlah.services.{BlogService, CurrentProjects, EmailService, MarkupService}

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(
  blogService        : BlogService,
  cc                 : MessagesControllerComponents,
  currentProjects    : CurrentProjects,
  emailService       : EmailService,
  markupService      : MarkupService,
  repository         : BlogPostRepository
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) {

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

    def latestBlog() = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getLatestBlogPost
      getFutureBlogPost.map(i => Ok(com.timlah.views.html.blog(i, markupService.markdownStringToHTML(i.content) match { case Left(_) => "<Unable to render the post at this time.>" case Right(s) => s})))
    }

    def blogPosts() = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getAllBlogPosts
      getFutureBlogPost.map(i => Ok(com.timlah.views.html.blogposts(i, i.size, markupService)))
    }

    def blogBySlug(slug: String) = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getBlogEntryBySlug(slug)
      getFutureBlogPost.map(i =>
        Ok(com.timlah.views.html.blog(
          i.get, markupService.markdownStringToHTML(i.get.content) match {
            case Left(_) => "<Unable to render the post at this time.>"
            case Right(s) => s
          }
        )))
    }

    def blogByID(id: Int) = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getBlogEntryById(id)
      getFutureBlogPost.map(i =>
        Ok(com.timlah.views.html.blog(
          i.get, markupService.markdownStringToHTML(i.get.content) match {
            case Left(_) => "<Unable to render the post at this time.>"
            case Right(s) => s
          }
        )))
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