package com.timlah.services.matchagana

import com.timlah.models.matchagana.Matchagana

import scala.util.Random

class MatchaganaService {
    private val clusters = Seq(
        "a", "i", "u", "e", "o", "ka", "ga", "ku", "gu",
        "ke", "ge", "ko", "go", "sa", "za", "si", "zi",
        "su", "zu", "se", "ze", "so", "zo", "ta", "da",
        "ti", "di", "tu", "du", "te", "de", "to", "do",
        "na", "ni", "nu", "ne", "no", "ha", "ba", "pa",
        "hi", "bi", "pi", "hu", "bu", "pu", "he", "be",
        "pe", "ho", "bo", "po", "ma", "mi", "mu", "me",
        "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re",
        "ro", "wa", "wo", "n"
    )

    val matchaganaSeq: Seq[Matchagana] = {
        clusters.map { cluster =>
            Matchagana.create(cluster)
        }
    }

    def startKanaToRomaji(count: Int = 10): Seq[Matchagana] = {
        Random.shuffle(matchaganaSeq).take(count)
    }
    
    def validateResults(matchaganaSeq: Seq[Matchagana], userResponses: Seq[String]) = {
    
    }
}

object MatchaganaService {

  private val default = new MatchaganaService()

  def startKanaToRomaji(count: Int = 10): Seq[Matchagana] = default.startKanaToRomaji(count)
}