package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;
import ddd.domain.models.Piece;
import ddd.domain.models.Square;

import java.util.Optional;

public class PieceCanNotAttackOwnColorRule implements ChessMoveRule {

    @Override
    public boolean isValid(Move move, Board board) {
        final Square endSquare = move.getEndSquare();
        final Piece piece = move.getPiece();
        final Optional<Piece> pieceAtTargetSquare = board.getPieceBySquare(endSquare);

        return pieceAtTargetSquare.isEmpty() || pieceAtTargetSquare.get().getColor() != piece.getColor();
    }

    @Override
    public String getFailureReason() {
        return "Target square is occupied by piece of the same color";
    }
}
