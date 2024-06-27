package com.timlah.controllers.potionseller

import akka.actor.ActorSystem
import play.api.mvc._

import scala.concurrent.{Await, ExecutionContext}
import javax.inject._

@Singleton
class PotionSellerController @Inject()(
  cc                        : MessagesControllerComponents,
  actorSystem               : ActorSystem
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) {
    def acceptPotion() = Action { implicit request: Request[AnyContent] =>
      Ok(com.timlah.views.html.index())
    }

    def declinePotion() = Action { implicit request: Request[AnyContent] =>
      Ok
    }
}
