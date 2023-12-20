package com.timlah.services

import requests.Response

import com.timlah.models.GravatarObject
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import java.security.MessageDigest
import scala.concurrent.Await
import scala.concurrent.duration._
import play.api.libs.json.Json

class GravatarProfileService @Inject()(implicit val ec: ExecutionContext) {
  def hashedEmailString(emailString: String) : String = {
    MessageDigest.getInstance("MD5").digest(emailString.getBytes).map("%02x".format(_)).mkString
  }

  def gravatarJson(emailString: String) : Future[String] = {
    val hash = hashedEmailString(emailString)
    Future {
      val response: Response = requests.get(s"https://www.gravatar.com/$hash.json")
      response.text()
    }
  }

  def gravatarObject(emailString: String) : GravatarObject = {
    val hash = hashedEmailString(emailString)
    val jsonFuture = gravatarJson(emailString)
    val jsonResult = Await.result(jsonFuture,1.second)
    
    jsonResult.asInstanceOf[GravatarObject]
  }
}
