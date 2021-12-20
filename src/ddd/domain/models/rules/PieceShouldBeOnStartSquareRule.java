package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;

public class PieceShouldBeOnStartSquareRule implements ChessMoveRule {
    @Override
    public boolean isValid(Move move, Board board) {
        return board.getPieceBySquare(move.getStartSquare()).isPresent() && board.getPieceBySquare(move.getStartSquare()).get().equals(move.getPiece());
    }

    @Override
    public String getFailureReason() {
        return "Piece cannot make this move because this piece is not at this square";
    }

}
