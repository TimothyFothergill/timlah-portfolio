package com.timlah.models.netbitpet

import play.api.libs.json._

import scala.language.postfixOps

case class PlayerNetBitPet(
    var petName         : String,
    var netbitpetType   : NetBitPet,
    var currentBoredom  : Int = 100,
    var currentHunger   : Int = 100,
    var currentHygiene  : Int = 100,
    var maxBoredom      : Int = 100,
    var maxHunger       : Int = 100,
    var maxHygiene      : Int = 100,
    var attack          : Int,
    var defence         : Int,
    var currentHealth   : Int,
    var maxHealth       : Int
)
