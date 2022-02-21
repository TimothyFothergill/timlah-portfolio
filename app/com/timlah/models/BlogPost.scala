package com.timlah.models

import akka.http.scaladsl.model.DateTime
import play.api.libs.json._

case class BlogPost(
  id          : Int               ,
  author      : Author            ,
  coauthor    : Option[Author]    ,
  title       : String            ,
  content     : String            ,
  date        : DateTime
)

object BlogPost {
  implicit val dateTimeFormat: OFormat[DateTime] = Json.format[DateTime]
  implicit val blogPostFormat: OFormat[BlogPost] = Json.format[BlogPost]
}
