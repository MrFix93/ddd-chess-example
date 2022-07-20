package ddd.domain.models;

import ddd.domain.util.Path;
import ddd.domain.util.Squares;
import lombok.Value;

import java.util.Arrays;
import java.util.List;

public record Move(Piece piece, Square startSquare, Square endSquare) {

    public enum MoveType {
        CAPTURE,
        NORMAL
    }

    public static Move of(Piece piece, String... patterns) {
        final List<Square> squares = Squares.asList(patterns);
        if (squares.size() != 2) {
            throw new IllegalArgumentException("Unable to instantiate move from patterns " + Arrays.toString(patterns));
        }

        return new Move(piece, squares.get(0), squares.get(1));
    }

    public List<Square> getPath() {
        final List<Square> travelingPath = Path.constructFrom(this);
        travelingPath.remove(this.startSquare());
        travelingPath.remove(this.endSquare());

        return travelingPath;
    }
}
