package hotciv.framework.variants.factories;

import hotciv.framework.variants.strategies.*;

public interface GameFactory {
    WinnerStrategy createWinnerStrategy();

    TimeStrategy createTimeStrategy();

    UnitActionStrategy createUnitActionStrategy();

    WorldLayoutStrategy createWorldLayoutStrategy();

    BattleStrategy createBattleStrategy();

}
