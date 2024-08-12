package com.timlah.controllers

import com.timlah.models.components.ExternalImage
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

@Singleton
class GalleryController @Inject()(
  cc                        : MessagesControllerComponents
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) {

    def kitacon2024() = Action { implicit request: Request[AnyContent] =>
      Ok(com.timlah.views.html.galleries.kitacon2024(Seq(
        // ExternalImage(None, "Smithing Station", "https://i.ibb.co/r353sPm/Smithing-Station.png"),
        // ExternalImage(Some("A Smithing Station in Skell's Quest"), "Smithing Station", "https://i.ibb.co/r353sPm/Smithing-Station.png"),
        // ExternalImage(Some("A Smithing Station in Skell's Quest"), "Smithing Station", "https://i.ibb.co/r353sPm/Smithing-Station.png"),
        // ExternalImage(Some("A Smithing Station in Skell's Quest"), "Smithing Station", "https://i.ibb.co/r353sPm/Smithing-Station.png"),
        // ExternalImage(Some("A Smithing Station in Skell's Quest"), "Smithing Station", "https://i.ibb.co/r353sPm/Smithing-Station.png"),
        // ExternalImage(Some("A Smithing Station in Skell's Quest"), "Smithing Station", "https://i.ibb.co/r353sPm/Smithing-Station.png")
      )))
    }
}
