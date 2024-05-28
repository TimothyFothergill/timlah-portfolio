package com.timlah.services.minigames

import scala.util.Random

class WordGameService {
  
    // eventually make sure these end up in their own separate file/service/something, or an api call from elsewhere...
    val ListOfWords         : Seq[String]   = Seq("HELLO","PLAYS","GAMES","CODER","APPLE")
    val NumberOfAttempts    : Int           = 5
    var inProgress          : Boolean       = false
    var attemptNumber       : Int           = 1
    var SelectedWord        : String        = ""

    // this would then be removed if ListOfWords gets from an api
    def selectRandomWord(): String = {
        ListOfWords(Random.between(0,5))
    }

    def setupGame(): Unit = {
        SelectedWord = selectRandomWord()
        inProgress = true
    }

    def compareSubmission(submission: String): Boolean = {
        userAttempt(submission)
        submission == SelectedWord
    }

    def userAttempt(submission: String): Unit = {
        if(submission == SelectedWord) {
            winner()
        } else {
            println("Continuing game...")
            if(attemptNumber == NumberOfAttempts) {
                loser()
            }
            attemptNumber += 1
        }
    }

    // do I want specific win and lose conditions?
    def winner(): Unit = {

    }

    def loser(): Unit = {
        println("You lost, correct word was: " + SelectedWord)
        reset()
    }

    def reset(): Unit = {
        attemptNumber = 0
        setupGame()
    }

    def continueGame(): Unit = {

    }    
}
