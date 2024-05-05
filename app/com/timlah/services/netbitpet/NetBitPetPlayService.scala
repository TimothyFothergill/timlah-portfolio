package com.timlah.services.netbitpet

import com.timlah.models.netbitpet._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

import akka.actor.ActorSystem
import javax.inject._

class NetBitPetPlayService @Inject()(implicit ec: ExecutionContext, actorSystem: ActorSystem) {

  var currentPet: Option[PlayerNetBitPet] = None
  var netBits: Int = 0

  def newNBPBirb(): NetBitPet = {
      var nbpName = "birb"
      NetBitPet(0,nbpName.capitalize,s"The ${nbpName.capitalize} makes things on high places go splatto", s"/images/netbitpet/${nbpName}.gif",1,10,1,Food(0,"Mixed Seeds","These seeds are ripe for the pecking."))
  }

  def newNBPCatto(): NetBitPet = {
      var nbpName = "catto"
      NetBitPet(1,nbpName.capitalize,s"The ${nbpName.capitalize} makes things on high places go splatto", s"/images/netbitpet/${nbpName}.gif",1,10,1,Food(1,"Canned Tuna","You can tune a piano, but you can't tune a fish."))
  }

  def newNBPDoggo(): NetBitPet = {
      var nbpName = "doggo"
      NetBitPet(2,nbpName.capitalize,s"The ${nbpName.capitalize} makes things on high places go splatto", s"/images/netbitpet/${nbpName}.gif",1,10,1,Food(2,"Diced Meat","What kind of meat, you ask? No i-deer. I'll see myself out..."))
  }

  def demoPet(): PlayerNetBitPet = {
    val petType: NetBitPet = newNBPCatto()
    PlayerNetBitPet("Mittens", petType, attack = 10, defence = 10, currentHealth = 100, maxHealth = 100)
  }

  def loadPet(netbitpet: PlayerNetBitPet): Unit = { currentPet = Some(netbitpet) }

  def feed(): Unit = {
    // later add food into this

  }

  def play(): Unit = {
    // later make play affect other stats

  }

  def wash(): Unit = {
    // later make washing pause other actions for a time

  }

  def train(): Unit = {
    // do not implement until everything else is done - this will change stats at a cost of NetBits

  }

  def minigame(): Unit = {
    // do not implement until absolutely everything is done

  }

  def increaseBoredom(amountToIncrease: Int): Unit = {
    currentPet match {
      case Some(netbitpet) => {netbitpet.currentBoredom += amountToIncrease}
    }
  }

  def decreaseFun(amountToDecrease: Int): Unit = {
    currentPet match {
      case Some(netbitpet) => {netbitpet.currentBoredom -= amountToDecrease}
    }
  }

  def increaseHunger(amountToIncrease: Int): Unit = {
    currentPet match {
      case Some(netbitpet) => {netbitpet.currentHunger += amountToIncrease}
    }
  }

  def decreaseHunger(amountToDecrease: Int): Unit = {
    currentPet match {
      case Some(netbitpet) => {netbitpet.currentHunger -= amountToDecrease}
    }
  }

  def increaseHygiene(amountToIncrease: Int): Unit = {
    currentPet match {
      case Some(netbitpet) => {netbitpet.currentHygiene += amountToIncrease}
    }
  }

  def decreaseHygiene(amountToDecrease: Int): Unit = {
    currentPet match {
      case Some(netbitpet) => {netbitpet.currentHygiene -= amountToDecrease}
    }
  }

  def updateApi(): Unit = {
    // 
  }

  def addNetBits(bitsToAdd: Int): Unit = {
    netBits = netBits + bitsToAdd
  }

  def subtractNetBits(bitsToSubtract: Int): Unit = {
    netBits = netBits - bitsToSubtract
  }


  def scheduleTasks(): Unit = {
    val schedulerActorSystem = ActorSystem("akka-scheduler-system")
    actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 20.seconds) {
      decreaseHunger(amountToDecrease = 10)
    }

    actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 25.seconds) {
      decreaseHygiene(amountToDecrease = 10)
    }

    actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 15.seconds) {
      decreaseFun(amountToDecrease = 10)
    }
    actorSystem.scheduler.schedule(initialDelay = 0.seconds, interval = 1.minute) {
      updateApi()
    }
  }
}

object NetBitPetPlayService {

  def newNBPCatto(): NetBitPet = {
      var nbpName = "catto"
      NetBitPet(1,nbpName.capitalize,s"The ${nbpName.capitalize} makes things on high places go splatto", s"/images/netbitpet/${nbpName}.gif",1,10,1,Food(1,"Canned Tuna","You can tune a piano, but you can't tune a fish."))
  }

  def demoPet(): PlayerNetBitPet = {
    val petType: NetBitPet = newNBPCatto()
    PlayerNetBitPet("Mittens", petType, attack = 10, defence = 10, currentHealth = 100, maxHealth = 100)
  }
}
