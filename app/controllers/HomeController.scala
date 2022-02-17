package controllers

import models.ContactData
import play.api.data._
import play.api.i18n._
import play.api.mvc._
import services.{CurrentProjects, EmailService}

import javax.inject._

@Singleton
class HomeController @Inject()(
  cc              : MessagesControllerComponents,
  currentProjects : CurrentProjects,
  emailService    : EmailService
) extends MessagesAbstractController(cc) {

    def index() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.index())
    }

    def about() = Action { implicit request: Request[AnyContent] =>
      Ok(views.html.about())
    }

    def projects() = Action { implicit request: Request[AnyContent] => {
      Ok(views.html.projects(currentProjects.currentProjects(), currentProjects.closedProjects()))
    }
  }
  def contactPage() = Action { implicit request: MessagesRequest[AnyContent] => {
      val boundForm = ContactData.contactForm
      Ok(views.html.contact(boundForm))
    }
  }

    def contactSubmit() = Action { implicit request: MessagesRequest[AnyContent] => {
      val boundForm = ContactData.contactForm.bindFromRequest()
      boundForm.fold(
        formWithErrors => {
          BadRequest(views.html.contact(formWithErrors))
        },
        submittedData => {
          val data = models.ContactData(submittedData.name, submittedData.email, submittedData.contents)
          emailService.sendEmail(
            data.name,
            data.email match {
              case Some(data) => data
              case None => "me@timlah.com"
            },
          data.contents)
          Redirect(routes.HomeController.index())
        }
      )

    }
  }
}