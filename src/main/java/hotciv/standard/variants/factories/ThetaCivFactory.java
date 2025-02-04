package hotciv.standard.variants.factories;

import hotciv.framework.GameConstants;
import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.variants.strategies.*;

public class ThetaCivFactory implements GameFactory {
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
        return new CaravanDecoratorUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DesertCustomizableWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsBattleStrategy();
    }
}
