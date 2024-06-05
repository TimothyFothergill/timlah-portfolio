package com.timlah.services.minigames

import scala.util.Random

class WordGameService {
  
    // eventually make sure these end up in their own separate file/service/something, or an api call from elsewhere...
    val ListOfWords         : Seq[String]       = Seq("HELLO","PLAYS","GAMES","CODER","APPLE")
    val NumberOfAttempts    : Int               = 5
    var inProgress          : Boolean           = false
    var hasWon              : Boolean           = false
    var hasLost             : Boolean           = false
    var attemptNumber       : Int               = 1
    var SelectedWord        : String            = ""
    var guessedWords        : Seq[String]       = Seq()
    var guessedWordObjects  : Seq[WordObject]   = Seq()

    // this would then be removed if ListOfWords gets from an api, if the api serves a /random endpoint
    def selectRandomWord(): String = {
        ListOfWords(Random.between(0,5))
    }

    def setupGame(): Unit = {
        if(!inProgress || hasWon || hasLost) {
            SelectedWord = selectRandomWord()
            inProgress = true
            print("new game: " + SelectedWord)
        }
    }

    def compareSubmission(submission: String): Boolean = {
        userAttempt(submission)
        submission == SelectedWord
    }

    def userAttempt(submission: String): Unit = {
        buildWordObject(submission)
        if(submission == SelectedWord) {
            winner(submission)
        } else {
            if(attemptNumber == NumberOfAttempts) { 
                loser(submission)
            } else {
                continueGame(submission)
            }
        }
    }

    // do I want specific win and lose conditions?
    def winner(submission: String): Unit = {
        println("in winner")
        attemptNumber += 1
        guessedWords = guessedWords.appended(submission)
        hasWon = true
        hasLost = false
        inProgress = false
    }

    def loser(submission: String): Unit = {
        println("You lost, correct word was: " + SelectedWord) 
        attemptNumber += 1
        guessedWords = guessedWords.appended(submission)
        hasWon = false
        hasLost = true
        inProgress = false
    }

    def reset(): Unit = { 
        inProgress          = false
        hasWon              = false
        hasLost             = false
        attemptNumber       = 1
        SelectedWord        = ""
        guessedWords        = Seq()
        guessedWordObjects  = Seq()
    }

    def buildWordObject(submission: String): Unit = {
        var wordObject: WordObject = null
        var characterObjects: Seq[CharacterObject] = Seq()
        submission.toUpperCase().zipWithIndex.foreach {
            case(character, index) => 
                if(SelectedWord.charAt(index) == character) {
                    if(SelectedWord.count(_ == character) > 1 && characterObjects.length > 0) {
                        val maybeCharacterObjects = characterObjects.find(_.character == character)
                        maybeCharacterObjects match {
                            case Some(value) => {
                                val updatedObject = value
                                updatedObject.status = 2
                                
                            }
                        }
                    }
                    characterObjects = characterObjects.appended(CharacterObject(character, 0))
                } else {
                    if(SelectedWord.contains(character)) {
                        val maybeCharacterObjects = characterObjects.find(_.character == character)
                        maybeCharacterObjects match {
                            case None => {
                                characterObjects = characterObjects.appended(CharacterObject(character, 1))
                            }
                            case Some(value) => {
                                if(SelectedWord.count(_ == character) > 0){
                                    characterObjects = characterObjects.appended(CharacterObject(character, 2))
                                } else {
                                    characterObjects = characterObjects.appended(CharacterObject(character, 1))
                                }
                            }
                        }
                    } else { 
                        characterObjects = characterObjects.appended(CharacterObject(character, 2))
                    }
                }
        }
        val areAllCharactersInTheCorrectPlace = characterObjects.forall { case CharacterObject(_, int) => int == 0 }
        if(areAllCharactersInTheCorrectPlace) {
            guessedWordObjects = guessedWordObjects.appended(WordObject(characterObjects, 0)) 
        } else {
            guessedWordObjects = guessedWordObjects.appended(WordObject(characterObjects, 1))
        }
    }

    def continueGame(submission: String): Unit = {
        attemptNumber += 1
        guessedWords = guessedWords.appended(submission)
        inProgress = true
    }
}

case class WordObject(
    // 0 = correct word, 1 = any number of errors
    word: Seq[CharacterObject],
    status: Int
)

case class CharacterObject(
    // 0 = correct letter in correct place (green), 1 = correct letter in wrong place (yellow), 2 = completely incorrect letter (grey)
    character: Char,
    var status: Int
)

object CharacterObject {
    def changeStatus(currentObject: CharacterObject, newStatus: Int): CharacterObject = {
        CharacterObject(character = currentObject.character, status = newStatus)
    }
}
