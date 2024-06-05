package com.timlah.services.minigames

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.mvc.QueryStringBindable

case class WordGameFormData(
    guess: String
)

object WordGameFormData {
    val wordGameForm: Form[WordGameFormData] = Form(
        mapping(
            "guess" -> nonEmptyText
        )(WordGameFormData.apply)(WordGameFormData.unapply)
    )
}
