package hotciv.framework.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface UnitActionStrategy {

    void performUnitActionAt(Position unitPosition, Game game);
}
