package com.timlah.models.minigames.tictactoe

case class TicTacToeBoard(
    boardSize: Int = 9,
    boardLayout: Seq[TicTacToeSquare],
    numberToLine: Int = 3
)

object TicTacToeBoard {
    val defaultBoard = TicTacToeBoard(
        boardSize = 9,
        boardLayout = Seq(
            TicTacToeSquare(1, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(2, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(3, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(4, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(5, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(6, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(7, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(8, Some(TicTacToePiece.NoPiece)),
            TicTacToeSquare(9, Some(TicTacToePiece.NoPiece)),
        ),
        numberToLine = 3
    )
}