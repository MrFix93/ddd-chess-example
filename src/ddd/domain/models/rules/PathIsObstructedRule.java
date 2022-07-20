package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;
import ddd.domain.models.Piece;

public class PathIsObstructedRule implements ChessMoveRule {
    @Override
    public boolean isValid(Move move, Board board) {
        final Piece piece = move.piece();

        return piece.canJumpOverPiece() || !pathIsObstructed(move, board);
    }

    private boolean pathIsObstructed(Move move, Board board) {
        return move.getPath().stream().anyMatch(square -> board.getPieceBySquare(square).isPresent());
    }

    @Override
    public String getFailureReason() {
        return "Path is obstructed";
    }
}
