package com.timlah.services

import akka.http.scaladsl.model.DateTime
import akka.http.scaladsl.model.headers.Date
import com.timlah.models.{Project, ProjectType}

import scala.concurrent.ExecutionContext.Implicits.global

class CurrentProjects {

  def currentProjects(): Seq[Project] = {
    Seq(
      Project(
        title       = "3D RPG Prototype",
        description = "A 3D RPG Prototype made in about a month. Will be working on semi-regular builds for this, so expect it to improve over time.",
        url         = "https://play.unity.com/mg/other/3d-rpg-prototype",
        projectTypes  = Seq(ProjectType.Unity),
        lastUpdated   = DateTime(2022,8,13)
      ),
      Project(
        title       = "Matchagana",
        description = "Match Hiragana to the Romaji equivalent - This was my first personal project in Python using the Flask framework. Latest update: New domain name!",
        url         = "https://www.matchagana.com",
        projectTypes  = Seq(ProjectType.Python),
        lastUpdated   = DateTime(2022,8,4)
      )
    )
  }

  def closedProjects(): Seq[Project] = {
    Seq(
      Project(
        title         = "Balloons Away",
        description   = "A game inspired by Helicopter game. Made in a day. Some flickering due to the speed of the obstacles. All assets and code by me, except the music track. Hold down the Left Mouse Button (LMB) to rise. Release LMB to fall. NOTE: You'll need to unmute the audio on the Unity Play WebGL player.",
        url           = "https://play.unity.com/mg/other/v0-1-ih5y",
        projectTypes  = Seq(ProjectType.Unity),
        lastUpdated   = DateTime(2022,2,27)
      ),
      Project(
        title         = "GeekOut South-West",
        description   = "Former organiser of a social group and blog based in Bristol. We celebrated geek culture in all of its forms!",
        url           = "http://geekoutsw.wordpress.com",
        projectTypes  = Seq(ProjectType.Wordpress),
        lastUpdated   = DateTime(2019,12,31)
      ),

    )
  }
}
