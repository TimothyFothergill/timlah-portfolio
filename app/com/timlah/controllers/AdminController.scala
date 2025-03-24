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
import com.timlah.repositories.BlogPostRepository

import play.Logger
import akka.http.scaladsl.model.DateTime
import scala.concurrent.Future

@Singleton
class AdminController @Inject()(
  adminService              : AdminService,
  blogService               : BlogPostRepository,
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

    def dashboard() = Action.async { implicit request: Request[AnyContent] =>
      val getFutureBlogPosts = blogService.getAllBlogPosts.map(_.sortBy(_.id))
      request.session.get("username") match {
        case Some(username) => {
          getFutureBlogPosts.map(i => Ok(com.timlah.views.html.admin.admindashboard(i, i.size)))
        }
        case None => {
          getFutureBlogPosts.map(i => Redirect(routes.HomeController.index())) // Hacky... Very hacky.
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

    def editPost(id: Int) = Action.async { implicit request: MessagesRequest[AnyContent] =>
      val futureBlogPost = blogService.getBlogEntryById(id)
      request.session.get("username") match {
        case Some(username) => {
          futureBlogPost.map(i => 
            i match {
                case Some(blog) => {
                  Ok(com.timlah.views.html.admin.admineditpost(
                    blog, 
                    NewBlogPostForm.newBlogPostForm.fill(NewBlogPostForm(
                      title   = blog.title,
                      slug    = blog.slug,
                      content = blog.content,
                      date    = ""
                    )
                    ))
                  )
                }
                case None => { Redirect(routes.AdminController.dashboard()) }
            }
          )
        }
        case None => {
          Future.successful(Redirect(routes.HomeController.index()))
        }
      }
    }

    def dropPost(id: Int) = Action { implicit request: MessagesRequest[AnyContent] => 
      request.session.get("username") match {
        case Some(username) => {
          adminService.dropBlogPostInDatabase(id)
          Redirect(routes.AdminController.dashboard())
        }
        case None => {
          Redirect(routes.HomeController.index())
        }
      }
    }

    def editBlogPostSubmit(id: Int) = Action { implicit request: MessagesRequest[AnyContent] => 
      val futureBlogPost = blogService.getBlogEntryById(id)
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
              adminService.editBlogPostInDatabase(submittedData, id)
              Redirect(routes.AdminController.dashboard())
            }
            case None => {
              Redirect(routes.HomeController.index())
            }
          }
        }
      )        
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
