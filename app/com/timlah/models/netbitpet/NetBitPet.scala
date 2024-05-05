package com.timlah.models.netbitpet

import play.api.libs.json._

import scala.language.postfixOps
import java.awt.Image

case class NetBitPet(
  id            : Int,
  name          : String,
  description   : String,
  iconPath      : String,
  attack        : Int,
  defence       : Int,
  health        : Int,
  favouriteFood : Food
)
