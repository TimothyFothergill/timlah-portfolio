package com.timlah.services.minigames

import scala.util.Random

class WordGameService {
  
    // eventually make sure these end up in their own separate file/service/something, or an api call from elsewhere...
    val ListOfWords         : Seq[String]   = Seq("HELLO","PLAYS","GAMES","CODER","APPLE")
    val NumberOfAttempts    : Int           = 5
    var inProgress          : Boolean       = false
    var attemptNumber       : Int           = 1
    var SelectedWord        : String        = ""
    var guessedWords        : Seq[String]   = Seq()

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
            if(attemptNumber == NumberOfAttempts) {
                loser()
            } else {
                continueGame(submission)
            }
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

    def buildWordObject(submission: String): WordObject = {
        var wordObject: WordObject = null
        submission.zipWithIndex.foreach {
            case(character, index) => if(submission.charAt(index) == character) {
                wordObject = WordObject(Seq(CharacterObject(character, 0)), 0)
            } else {
                if(submission.contains(character)) {
                wordObject = WordObject(Seq(CharacterObject(character, 1)), 0)
                } else {
                wordObject = WordObject(Seq(CharacterObject(character, 2)), 0)
                }
            }
        }
        wordObject
    }

    def continueGame(submission: String): Unit = {
        attemptNumber += 1
        guessedWords = guessedWords.appended(submission)
    }
}

case class WordObject(
    word: Seq[CharacterObject],
    status: Int
)

case class CharacterObject(
    character: Char,
    status: Int
)
