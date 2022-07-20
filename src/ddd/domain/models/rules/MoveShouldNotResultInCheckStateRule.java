package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;
import ddd.domain.models.Piece;

public class MoveShouldNotResultInCheckStateRule implements ChessMoveRule {

    @Override
    public boolean isValid(Move move, Board board) {
        final Piece piece = move.piece();

        final Board newBoard = Board.copy(board);
        newBoard.commit(move);

        return !CheckMateRule.isCheck(newBoard, piece.getColor());
    }

    @Override
    public String getFailureReason() {
        return "After this move your King could be captured";
    }

}
