package hotciv.standard.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.variants.strategies.UnitActionStrategy;

public class NoUnitActionStrategy implements UnitActionStrategy {

    @Override
    public void performUnitActionAt(Position unitPosition, Game game) {
        return;
    }
}
