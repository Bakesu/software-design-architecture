package hotciv.standard.testFactories;

import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.stubs.NoDiceRollStrategy;
import hotciv.standard.variants.factories.SemiCivFactory;
import hotciv.standard.variants.strategies.CalculatedBattleStrategy;

public class TestSemiCivFactory implements GameFactory {
    SemiCivFactory semiCivFactory;
    DiceRollStrategy diceRollStrategy;

    public TestSemiCivFactory() {
        this.semiCivFactory = new SemiCivFactory();
        this.diceRollStrategy = new NoDiceRollStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return semiCivFactory.createWinnerStrategy();
    }

    @Override
    public TimeStrategy createTimeStrategy() {
        return semiCivFactory.createTimeStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return semiCivFactory.createUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return semiCivFactory.createWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new CalculatedBattleStrategy(diceRollStrategy);
    }

    public DiceRollStrategy getDice(){return diceRollStrategy;}
}