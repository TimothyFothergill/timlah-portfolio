package com.timlah.models.matchagana

import com.ibm.icu.text.Transliterator

case class Matchagana(
    romaji: String,
    hiragana: String,
    katakana: String
)

object Matchagana {
    val toHiragana = Transliterator.getInstance("Latin-Hiragana")
    val toKatakana = Transliterator.getInstance("Latin-Katakana")

    def romajiToHiragana(c: String) = toHiragana.transform(c)
    def romajiToKatakana(c: String) = toKatakana.transform(c)

    def create(c: String): Matchagana = {
        Matchagana(
            romaji      = c,
            hiragana    = romajiToHiragana(c),
            katakana    = romajiToKatakana(c)
        )
    }
}