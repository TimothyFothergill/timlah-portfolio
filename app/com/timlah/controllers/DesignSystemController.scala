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
class DesignSystemController @Inject()(
  cc                        : MessagesControllerComponents
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) {
    def index() = Action { implicit request: Request[AnyContent] => {
        Ok(com.timlah.views.html.designsystem.index())
      }      
    }

    def menu() = Action { implicit request: Request[AnyContent] => {
        Ok(com.timlah.views.html.designsystem.menu())
      }
    }
}
