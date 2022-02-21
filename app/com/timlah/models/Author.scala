package com.timlah.models

import play.api.libs.json._

import scala.language.postfixOps

case class Author(
  id: Int,
  username: String,
  posts: Seq[BlogPost]
)

object Author {
  implicit val authorFormat: OFormat[Author] = Json.format[Author]
}

