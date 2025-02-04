package hotciv.standard.variants.factories;

import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.variants.strategies.*;

public class EpsilonCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ThreeStrikesWinnerStrategy();
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
        return new CalculatedBattleStrategy(new D6DiceRollStrategy());
    }
}
