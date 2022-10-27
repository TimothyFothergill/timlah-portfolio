package com.timlah.connectors

import com.timlah.models.WalkAboutWithMe
import play.api.libs.json.Json
import requests.Response

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WalkAboutWithMeConnector @Inject()(implicit val ec: ExecutionContext) {
  def getAllWalkAboutWithMeData: Future[Seq[WalkAboutWithMe]] = {
//    requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=45.395738&marker1long=141.702843&marker2lat=44.812031&marker2long=142.074650")
    val r: Response = requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=44.812031&marker1long=142.074650&marker2lat=44.34943456506206&marker2long=142.46409953518858")
    Future(Json.parse(r.text).as[Seq[WalkAboutWithMe]])
  }
}