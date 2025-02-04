package hotciv.standard.variants.factories;

import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.variants.strategies.*;

public class ZetaCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new CityConquestThenThreeStrikesWinnerStrategy();
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
        return new SimpleWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new AttackerWinsBattleStrategy();
    }
}
