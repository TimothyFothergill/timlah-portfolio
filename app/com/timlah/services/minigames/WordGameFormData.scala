package com.timlah.services.minigames

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.mvc.QueryStringBindable

case class WordGameFormData(
    guessChar1: Char,
    guessChar2: Char,
    guessChar3: Char,
    guessChar4: Char,
    guessChar5: Char,
)

object WordGameFormData {
    val wordGameForm: Form[WordGameFormData] = Form(
        mapping(
            "guess-char-1" -> char,
            "guess-char-2" -> char,
            "guess-char-3" -> char,
            "guess-char-4" -> char,
            "guess-char-5" -> char
        )(WordGameFormData.apply)(WordGameFormData.unapply)
    )
}
