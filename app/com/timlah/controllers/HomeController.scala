package com.timlah.controllers

import com.timlah.models.{BlogPost, ContactData, EnquiryType}
import com.timlah.repositories.BlogPostRepository
import com.timlah.services.{CurrentProjects, EmailService, GravatarProfileService, MarkupService}
import com.timlah.services.minigames.{WordGameService,WordGameFormData}
import com.timlah.services.minigames.tictactoe.TicTacToeService
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

import play.api.Logging
import com.timlah.models.minigames.tictactoe.TicTacToePiece

@Singleton
class HomeController @Inject()(
  cc                            : MessagesControllerComponents,
  currentProjects               : CurrentProjects,
  emailService                  : EmailService,
  gravatarProfileService        : GravatarProfileService,
  markupService                 : MarkupService,
  minigameWordGameService       : WordGameService,
  minigameTicTacToeGameService  : TicTacToeService,
  repository                    : BlogPostRepository,
  actorSystem                   : ActorSystem
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) with Logging  {

    def index() = Action { implicit request: Request[AnyContent] =>
        logger.info("GET: / ")
      Ok(com.timlah.views.html.index())
    }

    def about() = Action { implicit request: Request[AnyContent] =>
        logger.info("GET: /about ")
      Ok(com.timlah.views.html.about())
    }

    def projects() = Action { implicit request: Request[AnyContent] => {
        logger.info("GET: /projects ")
        Ok(com.timlah.views.html.projects(currentProjects.currentProjects(), currentProjects.closedProjects()))
      }
    }
    def contactPage() = Action { implicit request: MessagesRequest[AnyContent] => {
        logger.info("GET: /contact ")
        val boundForm = ContactData.contactForm
        val enquiryTypes = EnquiryType.values
        Ok(com.timlah.views.html.contact(boundForm, enquiryTypes.map(_.asString)))
      }
    }

    def latestBlog(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
      logger.info("GET: /latest-blog ")
      val getFutureBlogPost = repository.getLatestBlogPost
      val futureSlug = Await.result(getFutureBlogPost.map(i => i.slug), 3 seconds)
      Redirect(routes.HomeController.blogBySlug(futureSlug))
    }

    def blogPosts() = Action.async { implicit request: Request[AnyContent] =>
      logger.info("GET: /blog ")
      val getFutureBlogPost = repository.getAllBlogPosts.map(_.sortBy(_.id))
      getFutureBlogPost.map(i => Ok(com.timlah.views.html.blogposts(i, i.size, markupService)))
    }

    def blogBySlug(slug: String) = Action.async { implicit request: Request[AnyContent] =>
      logger.info(s"GET: /blog/slug/${slug} ")
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
      logger.info(s"GET: /blog/id/${id} ")
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
      logger.info("POST: /contact ")
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
          ||  data.contents.contains("CBD")
          ||  data.contents.contains("cbd")
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

// All minigame related controls - Should be moved to a new controller
def newTicTacToeGame() = Action { implicit request: MessagesRequest[AnyContent] => 
  logger.info("GET: /tic-tac-toe/new ")
    val maybeCookie = request.cookies.get("tictactoeData")
    maybeCookie match {
      case Some(cookie) => {
        if(isCookieExpired(maybeCookie.get)) {
          // cookie
          Redirect(routes.HomeController.setupNewTicTacToeGame())
        } else {
          // cookie active
          Redirect(routes.HomeController.currentTicTacToeGame())
        }        
      }
      case None => {
        // no cookie found
        Redirect(routes.HomeController.setupNewTicTacToeGame())
      } 
    }
}

def setupNewTicTacToeGame() = Action { implicit request: MessagesRequest[AnyContent] => 
  logger.info("GET: /tic-tac-toe/restart ")
  minigameTicTacToeGameService.reset()
  minigameTicTacToeGameService.setupGame()
  Redirect(routes.HomeController.currentTicTacToeGame())
}

def currentTicTacToeGame() = Action { implicit request: MessagesRequest[AnyContent] => 
  logger.info("GET: /tic-tac-toe/")
  Ok(com.timlah.views.html.games.tictactoe(minigameTicTacToeGameService.board.get, minigameTicTacToeGameService))  
}

def continueTicTacToeGame() = Action { implicit request: MessagesRequest[AnyContent] => 
  logger.info("POST: /tic-tac-toe/")
  request.body.asFormUrlEncoded.flatMap(_.get("tic-tac-toe-square-button").flatMap(_.headOption)) match {
    case Some(value) =>
      minigameTicTacToeGameService.updateBoard(value.toInt)
    case None => {}
  }
  Ok(com.timlah.views.html.games.tictactoe(minigameTicTacToeGameService.board.get, minigameTicTacToeGameService))  
}

  def newWordGame() = Action { implicit request: MessagesRequest[AnyContent] =>
    logger.info("GET: /word-up/new ")
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
    logger.info("GET: /word-up ")
    Ok(com.timlah.views.html.games.wordgame(WordGameFormData.wordGameForm, minigameWordGameService))
  }

  def continueWordGame() = Action { implicit request: MessagesRequest[AnyContent] =>
    logger.info("POST: /word-up ")
    val boundForm = WordGameFormData.wordGameForm.bindFromRequest()
    boundForm.fold(
      formWithErrors => {
        BadRequest(com.timlah.views.html.games.wordgame(formWithErrors, minigameWordGameService))
      },
      submittedData => {
        val guessedWord = 
          submittedData.guessChar1.toString.toUpperCase + 
          submittedData.guessChar2.toString.toUpperCase + 
          submittedData.guessChar3.toString.toUpperCase + 
          submittedData.guessChar4.toString.toUpperCase + 
          submittedData.guessChar5.toString.toUpperCase

        if (minigameWordGameService.compareSubmission(guessedWord)) {
          Redirect(routes.HomeController.currentWordGame()).flashing("success" -> "Great job!")
        } else {
          Redirect(routes.HomeController.currentWordGame())
        }
      }
    )
  }
  // End of minigame controls
}
