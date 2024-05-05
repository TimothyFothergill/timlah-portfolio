package com.timlah.controllers.netbitpet

import com.timlah.models.netbitpet.{NetBitPet,Food,PlayerNetBitPet}
import com.timlah.services.netbitpet._
import play.api.mvc._
import play.twirl.api.Html

import javax.inject._
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps

@Singleton
class NetBitPetController @Inject()(
  cc: MessagesControllerComponents,
  nbpPlayService    : NetBitPetPlayService,
)(implicit executionContext: ExecutionContext) extends MessagesAbstractController(cc) {

    def createPet() = Action { implicit request: MessagesRequest[AnyContent] => {
        Ok(com.timlah.components.netbitpet.html.createNetbitpet())
      }
    }

    // def newPet() = Action { implicit request: MessagesRequest[AnyContent] => {

    //   }
    // }

    def playerPetApi(playerNetBitPet: PlayerNetBitPet) = Action { implicit request: MessagesRequest[AnyContent] => {
        Ok(com.timlah.components.netbitpet.html.netbitpet(playerNetBitPet))
      }
    }
}
