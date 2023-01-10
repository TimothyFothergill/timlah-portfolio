package com.timlah.connectors

import com.timlah.models.WalkAboutWithMe
import play.api.libs.json.Json
import requests.Response

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WalkAboutWithMeConnector @Inject()(implicit val ec: ExecutionContext) {
  def getAllWalkAboutWithMeData: Future[Seq[WalkAboutWithMe]] = {
    val r: Response = requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=43.331180951043265&marker1long=141.861271595367&marker2lat=43.0556336617387&marker2long=141.35340782203917")
    Future(Json.parse(r.text).as[Seq[WalkAboutWithMe]])
  }
}
