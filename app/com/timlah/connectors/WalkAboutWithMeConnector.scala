package com.timlah.connectors

import com.timlah.models.WalkAboutWithMe
import play.api.libs.json.Json
import requests.Response

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WalkAboutWithMeConnector @Inject()(implicit val ec: ExecutionContext) {
  def getAllWalkAboutWithMeData: Future[Seq[WalkAboutWithMe]] = {
    val r: Response = requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=43.763066969072504&marker1long=142.35892064504907&marker2lat=43.331180951043265&marker2long=141.861271595367")
    Future(Json.parse(r.text).as[Seq[WalkAboutWithMe]])
  }
}
