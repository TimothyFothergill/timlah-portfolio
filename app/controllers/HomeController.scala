package controllers

import models.ContactData
import play.api.i18n.I18nSupport
import play.api.mvc._
import services.{CurrentProjects, EmailService}

import javax.inject._

@Singleton
class HomeController @Inject()(
  cc              : ControllerComponents,
  currentProjects : CurrentProjects,
  emailService    : EmailService
) extends AbstractController(cc) with I18nSupport {

    def index() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.index())
    }

    def about() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.about())
    }

    def projects() = Action { implicit request: Request[AnyContent] => {
      Ok(views.html.projects(currentProjects.currentProjects()))
    }
  }

    def contact() = Action { implicit request: Request[AnyContent] => {
      ContactData.contactForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.contact(formWithErrors))
        },
        submittedData => {
          val data = models.ContactData(submittedData.name, submittedData.email, submittedData.contents)
          println(data.name)
          data.email match {
            case Some(data)     => println(data)
            case None           => println("No email")
          }
          println(data.contents)
          emailService.sendEmail(
            data.name,
            data.email match {
              case Some(data) => data
              case None => "No Email"
            },
          data.contents)
          Redirect(routes.HomeController.contact())
        }
      )

    }
  }
}