package ddd.domain.models.pieces;

import lombok.EqualsAndHashCode;
import ddd.domain.models.ChessColor;
import ddd.domain.models.Piece;
import ddd.domain.models.strategies.AcrossMovingStrategy;
import ddd.domain.models.strategies.HorizontalMovingStrategy;
import ddd.domain.models.strategies.MovingStrategy;
import ddd.domain.models.strategies.VerticalMovingStrategy;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class Queen extends Piece {
    public Queen(ChessColor color) {
        super(color);
    }

    public static Piece black() {
        return new Queen(ChessColor.BLACK);
    }

    public static Piece white() {
        return new Queen(ChessColor.WHITE);
    }

    @Override
    protected List<MovingStrategy> getMovingStrategies() {
        return List.of(new HorizontalMovingStrategy(MovingStrategy.RangeMode.UNLIMITED),
                new VerticalMovingStrategy(MovingStrategy.RangeMode.UNLIMITED),
                new AcrossMovingStrategy(MovingStrategy.RangeMode.UNLIMITED)
        );
    }
}
