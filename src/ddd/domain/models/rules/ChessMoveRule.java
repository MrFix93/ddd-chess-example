package ddd.domain.models.rules;

import ddd.domain.models.Board;
import ddd.domain.models.Move;

public interface ChessMoveRule {
    boolean isValid(Move move, Board board);

    String getFailureReason();
}
