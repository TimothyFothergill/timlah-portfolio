package com.timlah.controllers.matchagana

import com.timlah.models.matchagana.Matchagana
import com.timlah.services.matchagana.MatchaganaService
import play.api.mvc._
import play.twirl.api.Html

import javax.inject._
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps
import java.lang.ProcessBuilder.Redirect

import play.api.mvc.{Cookie, Request, Result}
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import play.utils.UriEncoding
import play.api.libs.json.{Json, JsError, JsSuccess}

@Singleton
class MatchaganaController @Inject() (
    cc: MessagesControllerComponents,
    matchaganaService: MatchaganaService
)(implicit executionContext: ExecutionContext)
    extends MessagesAbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    {
      Ok(com.timlah.views.html.matchagana.matchaganaindex())
    }
  }

  def selectPlayMode(mode: String) = Action { implicit request: Request[AnyContent] =>
    {
        mode match {
            case "hiragana" => {
                Ok(com.timlah.views.html.matchagana.matchaganagame(matchaganaService.startKanaToRomaji(), mode))
            }
            case "katakana" => {
                Ok(com.timlah.views.html.matchagana.matchaganagame(Seq(), mode))
            }
            case _ => NotFound(com.timlah.views.html.notfound())
        }
    }
  }

  def displayResultsPage() = Action { implicit request: Request[AnyContent] => 
    {
      val maybeCookie = request.cookies.get("matchaganaActiveSession")
      println(maybeCookie.get.value)
      println(maybeCookie.get.value.getBytes("ISO-8859-1").map("%02X".format(_)).mkString(" "))
      maybeCookie match {
        case Some(cookie) => {
          val decoded = UriEncoding.decodePath(cookie.value, "UTF-8")
          println(decoded)
          val seqUserValues: Seq[String] = Json.parse(decoded).validate[Seq[String]].get
          println(seqUserValues)
          // matchaganaService.validateResults(initialMatchaganaSeq, seqUserValues)
          Redirect(routes.MatchaganaController.index())
        }
        case None => {
          println("no cookie found")
          Redirect(routes.MatchaganaController.index())
        }
      }
    }
  }
}
