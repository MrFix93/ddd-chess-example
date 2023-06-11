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
public class King extends Piece {
    public King(ChessColor color) {
        super(color);
    }

    public static Piece black() {
        return new King(ChessColor.BLACK);
    }

    public static Piece white() {
        return new King(ChessColor.WHITE);
    }

    @Override
    protected List<MovingStrategy> getMovingStrategies() {
        return List.of(new HorizontalMovingStrategy(MovingStrategy.RangeMode.SINGLE),
                new VerticalMovingStrategy(MovingStrategy.RangeMode.SINGLE),
                new AcrossMovingStrategy(MovingStrategy.RangeMode.SINGLE)
        );
    }
}
