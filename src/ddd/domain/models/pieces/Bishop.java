package ddd.domain.models.pieces;

import lombok.EqualsAndHashCode;
import ddd.domain.models.ChessColor;
import ddd.domain.models.Piece;
import ddd.domain.models.strategies.AcrossMovingStrategy;
import ddd.domain.models.strategies.MovingStrategy;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class Bishop extends Piece {
    public Bishop(ChessColor color) {
        super(color);
    }

    public static Piece black() {
        return new Bishop(ChessColor.BLACK);
    }

    public static Piece white() {
        return new Bishop(ChessColor.WHITE);
    }

    @Override
    protected List<MovingStrategy> getMovingStrategies() {
        return List.of(new AcrossMovingStrategy());
    }
}
