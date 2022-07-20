package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;
import ddd.domain.models.Piece;

public class TargetIsInAttackRangeRule implements ChessMoveRule {
    @Override
    public boolean isValid(Move move, Board board) {
        if (board.getMoveType(move) == Move.MoveType.NORMAL) {
            return true;
        }

        final Piece piece = move.piece();

        return piece.getAttackRange(move.startSquare()).contains(move.endSquare());
    }

    @Override
    public String getFailureReason() {
        return "Piece cannot make move";
    }

}
