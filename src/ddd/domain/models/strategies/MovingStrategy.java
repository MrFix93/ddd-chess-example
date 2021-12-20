package ddd.domain.models.strategies;

import ddd.domain.models.Navigator;
import ddd.domain.models.Square;

import java.util.List;

public interface MovingStrategy {

    List<Square> getRange(Square currentSquare);

    default List<Square> getAttackRange(Square currentSquare) {
        return getRange(currentSquare);
    }

    default Navigator getNavigator() {
        return Navigator.white();
    }

    enum RangeMode {
        SINGLE, UNLIMITED
    }
}
