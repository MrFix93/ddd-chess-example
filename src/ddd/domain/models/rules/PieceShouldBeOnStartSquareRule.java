package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;

public class PieceShouldBeOnStartSquareRule implements ChessMoveRule {
    @Override
    public boolean isValid(Move move, Board board) {
        return board.getPieceBySquare(move.startSquare()).isPresent() && board.getPieceBySquare(move.startSquare()).get().equals(move.piece());
    }

    @Override
    public String getFailureReason() {
        return "Piece cannot make this move because this piece is not at this square";
    }

}
