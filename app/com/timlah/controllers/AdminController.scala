package com.timlah.controllers

import play.api.mvc._
import play.twirl.api.Html
import javax.inject._
import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._
import scala.language.postfixOps
import java.lang.ProcessBuilder.Redirect

import com.timlah.models.admin.{AdminLoginDetails, NewBlogPostForm}
import com.timlah.services.admin.AdminService

import play.api.mvc.{Cookie, Request, Result}
import play.Logger
import akka.http.scaladsl.model.DateTime

@Singleton
class AdminController @Inject()(
  adminService              : AdminService,
  cc                        : MessagesControllerComponents,
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) {

    def login() = Action { implicit request: MessagesRequest[AnyContent] =>
      request.session.get("username") match {
        case Some(username) => {
          Redirect(routes.AdminController.dashboard())
        }
        case None => {
          val boundForm = AdminLoginDetails.adminLoginForm.bindFromRequest()
          Ok(com.timlah.views.html.admin.adminlogin(boundForm))
        }
      }
    }

    def dashboard() = Action { implicit request: Request[AnyContent] =>
      request.session.get("username") match {
        case Some(username) => {
          Ok(com.timlah.views.html.admin.admindashboard())
        }
        case None => {
          Redirect(routes.HomeController.index())
        }
      }
    }

    def logout() = Action { implicit request: Request[AnyContent] => {
      request.session.get("username") match {
          case Some(username) => {
            Redirect(routes.AdminController.login())
              .withNewSession
          }
          case None => {
            Redirect(routes.HomeController.index())
          }
        }
      }
    }

    def newPost() = Action { implicit request: MessagesRequest[AnyContent] =>
      request.session.get("username") match {
        case Some(username) => {
          val boundForm = NewBlogPostForm.newBlogPostForm.bindFromRequest()
          Ok(com.timlah.views.html.admin.adminnewpost(boundForm))
        }
        case None => {
          Redirect(routes.HomeController.index())
        }
      }
    }

    def newBlogPostSubmit() = Action { implicit request: MessagesRequest[AnyContent] => 
      val boundForm = NewBlogPostForm.newBlogPostForm.bindFromRequest()
      boundForm.fold(
        formWithErrors => {
          BadRequest(
            com.timlah.views.html.admin.adminlogin(formWithErrors) // update to NewBlogPost when possible
          )
        },
        submittedData => {
          request.session.get("username") match {
            case Some(username) => {
              adminService.addNewBlogPostToDatabase(submittedData)
              Redirect(routes.AdminController.dashboard())
            }
            case None => {
              Redirect(routes.HomeController.index())
            }
          }
        }
      )
        
    }

    def loginSubmit() = Action { implicit request: MessagesRequest[AnyContent] => 
        val boundForm = AdminLoginDetails.adminLoginForm.bindFromRequest()
        boundForm.fold(
            formWithErrors => {
              BadRequest(
                  com.timlah.views.html.admin.adminlogin(formWithErrors)
              )
            },
            submittedData => {
                val data = AdminLoginDetails(submittedData.username, submittedData.password)
                var authenticated = adminService.checkUserDetails(data)
                if(authenticated) {
                  Logger.info(s"Successful sign-in: ${submittedData.username} @ ${DateTime.now}")
                  Redirect(routes.AdminController.dashboard())
                    .withNewSession
                    .withSession("username" -> submittedData.username)
                } else {
                  BadRequest(
                    com.timlah.views.html.admin.adminlogin(boundForm)
                  )
                }
            })
    }
}
