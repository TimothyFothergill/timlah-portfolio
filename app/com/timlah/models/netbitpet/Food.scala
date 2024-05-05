package com.timlah.models.netbitpet

import play.api.libs.json._

import scala.language.postfixOps

case class Food(
  id: Int,
  name: String,
  description: String
)
