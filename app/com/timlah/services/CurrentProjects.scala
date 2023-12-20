package com.timlah.services

import akka.http.scaladsl.model.DateTime
import akka.http.scaladsl.model.headers.Date
import com.timlah.models.{Project, ProjectType}

import scala.concurrent.ExecutionContext.Implicits.global

class CurrentProjects {

  def currentProjects(): Seq[Project] = {
    Seq(
      Project(
        title       = "Skell's Quest",
        description = "A 3D RPG that I'm working on over time. Every 2-4 months I tend to get a new build out, depending on the complexity of the build. Latest build full of UI and Performance fixes, as well as achievements and quality of life features.",
        url         = "https://play.unity.com/mg/other/skell-s-quest-v0-0-4-prototype",
        projectTypes  = Seq(ProjectType.Unity),
        lastUpdated   = DateTime(2023,7,28)
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
      Project(
        title       = "giphy2clippy",
        description = "A python script that takes CLI arguments and spits out a random gif. Also has github formatting support, for quick and easy 'LGTM' gifs.",
        url         = "https://github.com/TimothyFothergill/giphy2clippy",
        projectTypes  = Seq(ProjectType.Python),
        lastUpdated   = DateTime(2022,9,15)
      ),
    )
  }
}
