package com.timlah.services

import com.timlah.models.Project

import scala.concurrent.ExecutionContext.Implicits.global

class CurrentProjects {

  def currentProjects(): Seq[Project] = {
    Seq(
      Project(
        title       = "Balloons Away",
        description = "A game inspired by Helicopter game. Made in a day. Some flickering due to the speed of the obstacles. All assets and code by me, except the music track. Hold down the Left Mouse Button (LMB) to rise. Release LMB to fall. NOTE: You'll need to unmute the audio on the Unity Play WebGL player.",
        url         = "https://play.unity.com/mg/other/v0-1-ih5y"
      ),
      Project(
        title       = "Matchagana",
        description = "Match Hiragana to the Romaji equivalent - This was my first personal project in Python using the Flask framework. NOTE: This is hosted on a free Heroku plan. It can take a while to load initially.",
        url         = "http://matchagana.herokuapp.com"
      )
    )
  }

  def closedProjects(): Seq[Project] = {
    Seq(
      Project(
        title       = "GeekOut South-West [CLOSED]",
        description = "Former organiser of a social group and blog based in Bristol. We celebrated geek culture in all of its forms!",
        url         = "http://geekoutsw.wordpress.com"
      ),

    )
  }
}
