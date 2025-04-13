package com.timlah.services.minigames

import scala.util.Random
import scala.io.Source

class WordGameService {
  
    val words               : Seq[String]       = loadWords("resources/words.txt")
    val NumberOfAttempts    : Int               = 6
    var inProgress          : Boolean           = false
    var hasWon              : Boolean           = false
    var hasLost             : Boolean           = false
    var attemptNumber       : Int               = 0
    var SelectedWord        : String            = ""
    var guessedWords        : Seq[String]       = Seq()
    var guessedWordObjects  : Seq[WordObject]   = Seq()

    def loadWords(filePath: String): Seq[String] = {
        val source = Source.fromFile(filePath)
        try {
            source.getLines().toSeq
        } finally {
            source.close()
        }
    }

    def selectRandomWord(): String = {
        val randomIndex = Random.nextInt(words.size)
        words(randomIndex)
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

    def winner(submission: String): Unit = {
        attemptNumber += 1
        guessedWords = guessedWords.appended(submission)
        hasWon = true
        hasLost = false
        inProgress = false
    }

    def loser(submission: String): Unit = {
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
  val submissionCounts = scala.collection.mutable.Map[Char, Int]().withDefaultValue(0)
  val selectedWordCounts = SelectedWord.groupBy(identity).view.mapValues(_.length).toMap

  submission.toUpperCase().zipWithIndex.foreach {
    case (character, index) =>
      submissionCounts(character) += 1

      if (SelectedWord.charAt(index) == character) {
        characterObjects = characterObjects.appended(CharacterObject(character, 0))
      } else {
        if (SelectedWord.contains(character)) {
          // Check if the character has been used more than it exists in the SelectedWord
          if (submissionCounts(character) <= selectedWordCounts.getOrElse(character, 0)) {
            characterObjects = characterObjects.appended(CharacterObject(character, 1))
          } else {
            characterObjects = characterObjects.appended(CharacterObject(character, 2))
          }
        } else {
          characterObjects = characterObjects.appended(CharacterObject(character, 2))
        }
      }
  }

  // Post-process to update previously checked characters
  characterObjects.zipWithIndex.foreach {
    case (CharacterObject(character, status), index) if status == 1 =>
      if (submissionCounts(character) > selectedWordCounts.getOrElse(character, 0)) {
        characterObjects = characterObjects.updated(index, CharacterObject(character, 2))
        submissionCounts(character) -= 1
      }
    case _ =>
  }

  val areAllCharactersInTheCorrectPlace = characterObjects.forall { case CharacterObject(_, int) => int == 0 }
  if (areAllCharactersInTheCorrectPlace) {
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
