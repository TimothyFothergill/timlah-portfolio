package com.timlah.models

import play.api.libs.json.{Json, OFormat, __}
import play.api.libs.functional.syntax._

case class GravatarObject(
  profileUrl      : String,
  displayName     : String,
  aboutMe         : String,
  currentLocation : String,
  accounts        : Seq[Account],
  gravatarUrls    : Seq[GravatarUrlObject]
)

object GravatarObject {
  implicit val gravatarFormat: OFormat[GravatarObject] = (
    (__ \ "profileUrl"        ).format[String]
    ~ (__ \ "displayName"     ).format[String]
    ~ (__ \ "aboutMe"         ).format[String]
    ~ (__ \ "currentLocation" ).format[String]
    ~ (__ \ "accounts"        ).format[Seq[Account]]
    ~ (__ \ "gravatarUrls"    ).format[Seq[GravatarUrlObject]]
  )(GravatarObject.apply _, unlift(GravatarObject.unapply))

  def parseFromJson(jsonString: String): GravatarObject = {
    Json.parse(jsonString).as[GravatarObject]
  }
}

case class Account(
   domain   : String,
   display  : String,
   url      : String,
   iconUrl  : String,
   username : String,
   verified : Boolean,
   name     : String,
   shortname: String
)
object Account {
  implicit val accountFormat: OFormat[Account] = (
    (__ \ "domain"      ).format[String]
    ~ (__ \ "display"   ).format[String]
    ~ (__ \ "url"       ).format[String]
    ~ (__ \ "iconUrl"   ).format[String]
    ~ (__ \ "username"  ).format[String]
    ~ (__ \ "verified"  ).format[Boolean]
    ~ (__ \ "name"      ).format[String]
    ~ (__ \ "shortname" ).format[String]
  )(Account.apply _, unlift(Account.unapply))
}

case class GravatarUrlObject(
  value: String,
  title: String
)
object GravatarUrlObject {
  implicit val gravatarUrlFormat: OFormat[GravatarUrlObject] = (
    (__ \ "value"   ).format[String]
    ~ (__ \ "title" ).format[String]
  )(GravatarUrlObject.apply _, unlift(GravatarUrlObject.unapply))
}