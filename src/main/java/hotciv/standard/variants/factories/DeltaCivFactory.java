package hotciv.standard.variants.factories;

import hotciv.framework.GameConstants;
import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.variants.strategies.*;

public class DeltaCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new RedWinsIn3000BCWinnerStrategy();
    }

    @Override
    public TimeStrategy createTimeStrategy() {
        return new HundredYearsTimeStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new NoUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new CustomizableWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsBattleStrategy();
    }
}
