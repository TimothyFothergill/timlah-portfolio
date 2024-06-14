package com.timlah.services.minigames
import play.api.libs.json._

case class CookieData(isComplete: Boolean, word: String, turnsTaken: Int)

object CookieData {
  implicit val format: Format[CookieData] = Json.format[CookieData]
}