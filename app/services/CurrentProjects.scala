package services

import models.Project

import scala.concurrent.ExecutionContext.Implicits.global

class CurrentProjects {

  def currentProjects(): Seq[Project] = {
    Seq(
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
