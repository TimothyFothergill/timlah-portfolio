package com.timlah.services.minigames.tictactoe

object TicTacToeManager {
  private val games = scala.collection.concurrent.TrieMap[String, TicTacToeService]()

  def getOrCreateGame(sessionId: String): TicTacToeService = {
    games.getOrElseUpdate(sessionId, {
      val newGame = new TicTacToeService()
      newGame.setupGame()
      newGame
    })
  }

  def resetGame(sessionId: String): TicTacToeService = {
    val newGame = new TicTacToeService()
    newGame.setupGame()
    games.put(sessionId, newGame)
    newGame
  }
}
