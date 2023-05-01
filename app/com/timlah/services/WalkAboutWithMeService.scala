package com.timlah.services

import com.timlah.connectors.WalkAboutWithMeConnector
import requests.get
import sys.process._
import java.net.URL
import java.io.File
import scala.language.postfixOps

class WalkAboutWithMeService {

  def downloadImage(imageURL: Seq[String], imageDate: Seq[String], path: String): Unit = {
   imageURL.foreach(image => {
     imageDate.foreach(date => {
      new URL(image) #> new File("public/images/walk-images/Walk " + date.replace("00:00:00 GMT","") + ".png") !!
     })
   })
  }
}
