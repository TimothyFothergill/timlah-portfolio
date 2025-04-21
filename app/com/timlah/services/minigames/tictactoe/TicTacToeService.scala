package com.timlah.services.minigames.tictactoe

import scala.util.Random
import com.timlah.models.minigames.tictactoe.TicTacToeBoard
import com.timlah.models.minigames.tictactoe.TicTacToeSquare
import com.timlah.models.minigames.tictactoe.TicTacToePiece

class TicTacToeService {

    var board: Option[TicTacToeBoard] = None
    var hasWon              : Boolean = false
    var hasLost             : Boolean = false
    var isDraw              : Boolean = false
    var opponentPiece       : TicTacToePiece = TicTacToePiece.NoPiece
    var playerPiece         : TicTacToePiece = TicTacToePiece.NoPiece
    var winConditions       : Seq[(Int, Int, Int)] = Seq(
        (1,2,3),
        (4,5,6),
        (7,8,9),
        (1,5,9),
        (1,4,7),
        (2,5,8),
        (3,6,9),
        (3,5,7)
    )

    def setupGame() = {
        if(hasWon || hasLost || board.isEmpty) {
            board = Some(TicTacToeBoard.defaultBoard)
            val randomSelector = Random.between(0,2)
            randomSelector match {
                case 0 => {
                    playerPiece = TicTacToePiece.OPiece
                    opponentPiece = TicTacToePiece.XPiece
                }
                case _ => {
                    playerPiece = TicTacToePiece.XPiece
                    opponentPiece = TicTacToePiece.OPiece
                }
            }
        }
    }

    def updateBoard(playerPiecePosition: Int) = {
        val currentBoard = board.get
        if(currentBoard.boardLayout.filter(_.state == Some(TicTacToePiece.NoPiece)).length == 0) { isDraw = true }
        val updatedLayout = currentBoard.boardLayout.updated(playerPiecePosition - 1, TicTacToeSquare(playerPiecePosition, Some(playerPiece)))
        val updatedBoard = currentBoard.copy(boardLayout = updatedLayout)
        board = Some(updatedBoard)
        if(!checkForWinCondition()) {
            botDecision()
        }
    }

    def botDecision() = {
        val currentBoard = board.get
        val emptySquares = currentBoard.boardLayout.filter(_.state == Some(TicTacToePiece.NoPiece))
        if(emptySquares.length > 0) {
            val randomSquare = emptySquares(Random.nextInt(emptySquares.size))
            
            val updatedLayout = currentBoard.boardLayout.updated(randomSquare.id - 1, TicTacToeSquare(randomSquare.id, Some(opponentPiece)))
            val updatedBoard = currentBoard.copy(boardLayout = updatedLayout)
            board = Some(updatedBoard)
        }
        checkForWinCondition()
    }

    def checkForWinCondition(): Boolean = {
        val oPieces = board.get.boardLayout.filter(_.state == Some(TicTacToePiece.OPiece))
        val xPieces = board.get.boardLayout.filter(_.state == Some(TicTacToePiece.XPiece))

        if(hasWon(oPieces, winConditions)){ 
            if(playerPiece == TicTacToePiece.OPiece) { hasWon = true } else { hasLost = true }
        }
        if(hasWon(xPieces, winConditions)){ 
            if(playerPiece == TicTacToePiece.XPiece) { hasWon = true } else { hasLost = true }
        }
        hasWon || hasLost
    }

    def hasWon(pieces: Seq[TicTacToeSquare], winConditions: Seq[(Int, Int, Int)]): Boolean = {
        val occupiedIds = pieces.map(_.id)

        winConditions.exists { case (a, b, c) =>
            occupiedIds.contains(a) && occupiedIds.contains(b) && occupiedIds.contains(c)
        }
    }

    def reset() = {
        board         = None
        hasWon        = false
        hasLost       = false
        isDraw        = false
        opponentPiece = TicTacToePiece.NoPiece
        playerPiece   = TicTacToePiece.NoPiece
    }
}
