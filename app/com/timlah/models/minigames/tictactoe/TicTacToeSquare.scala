package com.timlah.models.minigames.tictactoe

case class TicTacToeSquare(
    id: Int,
    state: Option[TicTacToePiece]
)

sealed trait TicTacToePiece {
    def asString: String
}
object TicTacToePiece {
    case object NoPiece extends TicTacToePiece { def asString: String = "-" }
    case object OPiece extends TicTacToePiece  { def asString: String = "O" }
    case object XPiece extends TicTacToePiece  { def asString: String = "X" }
}
