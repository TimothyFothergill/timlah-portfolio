package com.timlah.controllers

import com.timlah.connectors.WalkAboutWithMeConnector
import com.timlah.models.{BlogPost, ContactData, EnquiryType}
import com.timlah.repositories.BlogPostRepository
import com.timlah.services.{CurrentProjects, EmailService, GravatarProfileService, MarkupService, WalkAboutWithMeService}
import com.timlah.services.minigames.{WordGameService,WordGameFormData}
import play.api.mvc._
import play.twirl.api.Html

import akka.actor.ActorSystem
import javax.inject._
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps
import java.lang.ProcessBuilder.Redirect

import play.api.mvc.{Cookie, Request, Result}
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import com.timlah.models.components.button.Button
import com.timlah.components.button.html.StandardButton

@Singleton
class HomeController @Inject()(
  cc                        : MessagesControllerComponents,
  currentProjects           : CurrentProjects,
  emailService              : EmailService,
  gravatarProfileService    : GravatarProfileService,
  markupService             : MarkupService,
  minigameWordGameService   : WordGameService,
  repository                : BlogPostRepository,
  actorSystem               : ActorSystem
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

    def latestBlog(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getLatestBlogPost
      val futureSlug = Await.result(getFutureBlogPost.map(i => i.slug), 3 seconds)
      Redirect(routes.HomeController.blogBySlug(futureSlug))
    }

    def blogPosts() = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getAllBlogPosts.map(_.sortBy(_.id))
      getFutureBlogPost.map(i => Ok(com.timlah.views.html.blogposts(i, i.size, markupService)))
    }

    def blogBySlug(slug: String) = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getBlogEntryBySlug(slug)
      getFutureBlogPost.map(i =>
        Ok(com.timlah.views.html.blog(
          i.get, markupService.markdownStringToHTML(i.get.content) match {
            case Left(_) => "<Unable to render the post at this time.>"
            case Right(s) => s
          },
          Some(generateBlogMetaTags(i.get, markupService.markdownStringToHTML(i.get.content) match {
            case Left(_) => "<Unable to render the post at this time.>"
            case Right(s) => s
          }))
        )))
    }

    def blogByID(id: Int) = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPost = repository.getBlogEntryById(id)
      getFutureBlogPost.map(i =>
        Ok(com.timlah.views.html.blog(
          i.get, markupService.markdownStringToHTML(i.get.content) match {
            case Left(_) => "<Unable to render the post at this time.>"
            case Right(s) => s
          },
          Some(generateBlogMetaTags(i.get, markupService.markdownStringToHTML(i.get.content) match {
            case Left(_) => "<Unable to render the post at this time.>"
            case Right(s) => s
          }))
        )))
    }

  def generateBlogMetaTags(latest: BlogPost, blogContent: String): Html = {
    val regexMatchesImage = """!\[.*]\((.*)\)""".r.findFirstMatchIn(latest.content).map(_.group(1)).getOrElse("")
    Html(s"""<meta property='og:description' content='${blogContent.split("\n").headOption.getOrElse("")}'>
        <meta property='og:title' content='${latest.title}'>
        <meta property='og:image' content='${regexMatchesImage}'>
        <meta property="og:url" content="https://www.timlah.com${com.timlah.controllers.routes.HomeController.blogBySlug(latest.slug)}">
        <meta property="og:site_name" content="Timlah's Techs">
        <meta property="og:type" content="website">
        <meta property="article:author" content="Timlah">
        <meta name="fediverse:creator" content="@timlah@tech.lgbt">
        <meta name="twitter:card" content="summary_large_image">""")
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
          ||  data.contents.contains("SEO")
          ||  data.contents.contains("medicine")
          ||  data.contents.contains("cialis")
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

  def getCookieExpiry(cookie: Cookie): Option[ZonedDateTime] = {
    cookie.maxAge.map { maxAge =>
      ZonedDateTime.now().plusSeconds(maxAge)
    }
  }

  def isCookieExpired(cookie: Cookie): Boolean = {
    getCookieExpiry(cookie) match {
      case Some(expiryTime) => {
        if(expiryTime.isBefore(ZonedDateTime.now())){true}else{false}
      }
      case None => false
    }
  }

  def newWordGame() = Action { implicit request: MessagesRequest[AnyContent] =>
    val maybeCookie = request.cookies.get("wordupData")
    maybeCookie match {
      case Some(cookie) => {
        if(isCookieExpired(maybeCookie.get)) {
          // cookie
          Redirect(routes.HomeController.setupNewWordGame())
        } else {
          // cookie active
          Redirect(routes.HomeController.currentWordGame())
        }        
      }
      case None => {
        // no cookie found
        Redirect(routes.HomeController.setupNewWordGame())
      } 
    }
  }

  def setupNewWordGame() = Action { implicit request: MessagesRequest[AnyContent] =>
    minigameWordGameService.reset()
    minigameWordGameService.setupGame()
    Redirect(routes.HomeController.currentWordGame())
  }

  def currentWordGame() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(com.timlah.views.html.wordgame(WordGameFormData.wordGameForm, minigameWordGameService))
  }

  def continueWordGame() = Action { implicit request: MessagesRequest[AnyContent] =>
    println("Game in progress, continuing")
    val boundForm = WordGameFormData.wordGameForm.bindFromRequest()
    boundForm.fold(
      formWithErrors => {
        BadRequest(com.timlah.views.html.wordgame(formWithErrors, minigameWordGameService))
      },
      submittedData => {
        val data = WordGameFormData(submittedData.guess.toUpperCase())
        if (minigameWordGameService.compareSubmission(data.guess)) {
          Redirect(routes.HomeController.currentWordGame()).flashing("success" -> "Great job!")
        } else {
          Redirect(routes.HomeController.currentWordGame())
        }
      }
    )
  }
}
