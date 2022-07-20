package ddd.domain.models;

import lombok.EqualsAndHashCode;
import ddd.domain.GameAggregate;
import ddd.domain.exceptions.IllegalChessMoveException;
import ddd.domain.models.rules.ChessMoveRule;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public record Board(Map<Square, Piece> piecesOnSquare) {
    public Board(Map<Square, Piece> piecesOnSquare) {
        this.piecesOnSquare = piecesOnSquare;
    }

    public static Board copy(Board board) {
        return new Board(new HashMap<>(board.piecesOnSquare));
    }

    public Board commit(Move move) {
        Board copy = Board.copy(this);
        copy.piecesOnSquare.remove(move.startSquare());
        copy.piecesOnSquare.put(move.endSquare(), move.piece());

        return copy;
    }

    public Board make(Move move, Board board) throws IllegalChessMoveException {
        for (ChessMoveRule rule : GameAggregate.rules) {
            final boolean moveAdheresRule = rule.isValid(move, board);
            if (!moveAdheresRule) {
                throw new IllegalChessMoveException(rule.getFailureReason(), move, board);
            }
        }

        return board.commit(move);
    }

    public boolean isValidMove(Move move) {
        return GameAggregate.rules.stream().allMatch(rule -> rule.isValid(move, this));
    }

    public Move.MoveType getMoveType(Move move) {
        final Optional<Piece> pieceBySquare = getPieceBySquare(move.endSquare());
        if (pieceBySquare.isPresent() && pieceBySquare.get().getColor() == move.piece().getColor().invert()) {
            return Move.MoveType.CAPTURE;
        }
        return Move.MoveType.NORMAL;
    }

    public Optional<Square> getPieceByTypeAndColor(ChessColor color, Class<? extends Piece> pieceType) {
        return getPieceByColor(color).entrySet().stream()
                .filter(entry -> entry.getValue().getClass() == pieceType)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public Optional<Piece> getPieceBySquare(Square square) {
        return piecesOnSquare.entrySet().stream()
                .filter(position -> position.getKey().equals(square))
                .map(Map.Entry::getValue)
                .findFirst();
    }

    public Map<Square, Piece> getPieceByColor(ChessColor color) {
        return piecesOnSquare.entrySet().stream()
                .filter(position -> position.getValue().getColor().equals(color))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
