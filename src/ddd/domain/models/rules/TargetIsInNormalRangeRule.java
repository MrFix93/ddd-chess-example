package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;
import ddd.domain.models.Piece;

public class TargetIsInNormalRangeRule implements ChessMoveRule {
    @Override
    public boolean isValid(Move move, Board board) {
        if (board.getMoveType(move) == Move.MoveType.CAPTURE) {
            return true;
        }

        final Piece piece = move.getPiece();

        return piece.getRange(move.getStartSquare()).contains(move.getEndSquare());
    }

    @Override
    public String getFailureReason() {
        return "Piece cannot make move";
    }

}
