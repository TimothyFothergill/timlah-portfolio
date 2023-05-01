package com.timlah.controllers

import com.timlah.connectors.WalkAboutWithMeConnector
import com.timlah.models.{ContactData, EnquiryType}
import com.timlah.repositories.BlogPostRepository
import com.timlah.services.{CurrentProjects, EmailService, MarkupService, WalkAboutWithMeService}
import play.api.mvc._
import play.api.cache
import play.api.cache.Cached

import javax.inject._
import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._
import scala.language.postfixOps

@Singleton
class HomeController @Inject()(
  cc                        : MessagesControllerComponents,
  currentProjects           : CurrentProjects,
  emailService              : EmailService,
  markupService             : MarkupService,
  repository                : BlogPostRepository,
  walkAboutWithMeConnector  : WalkAboutWithMeConnector,
  WalkAboutWithMeService    : WalkAboutWithMeService
)(implicit executionContext: ExecutionContext, cached: Cached) extends MessagesAbstractController(cc) {

    def index() = Action { implicit request: Request[AnyContent] =>
      Ok(com.timlah.views.html.index())
    }

    def about() = Action { implicit request: Request[AnyContent] =>
      Ok(com.timlah.views.html.about())
    }

    def walkaboutwithme() = cached.status(_ => "walkAboutWithMe", 200, duration = 5.minutes) {
      Action.async { implicit request: Request[AnyContent] =>
        val getFutureWalkAboutData = walkAboutWithMeConnector.getAllWalkAboutWithMeData
        getFutureWalkAboutData.map(o => WalkAboutWithMeService.downloadImage(o.map(_.progressImageURL), o.map(_.date), "/public/images/walk-images"))
        getFutureWalkAboutData.map(i => Ok(com.timlah.views.html.walkaboutwithme(i)))
      }
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

    def latestBlog(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getLatestBlogPost
      val futureSlug = Await.result(getFutureBlogPost.map(i => i.slug), 3 seconds)
      Redirect(routes.HomeController.blogBySlug(futureSlug))
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
          if (
              data.contents.contains("bitcoin")
          ||  data.contents.contains("BTC")
          ||  data.contents.contains("btc")
          ||  data.contents.contains("hacker")
          ||  data.contents.contains("hacked")
          ) {
            Redirect(routes.HomeController.index()).flashing("fail" -> "Contact message not sent.")
          } else {
            emailService.sendEmail(
              subject = data.subject match {
                case Some(data) => data
                case None => "Enquiry from timlah.com"
              },
              name = data.name,
              address = data.email,
              enquiry = submittedData.enquiry,
              content = data.contents
            )
            Redirect(routes.HomeController.index()).flashing("success" -> "Contact message sent, thank you.")
          }
        }
      )
    }
  }
}