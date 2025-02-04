package hotciv.standard.testFactories;

import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.stubs.NoDiceRollStrategy;
import hotciv.standard.variants.factories.EpsilonCivFactory;
import hotciv.standard.variants.strategies.CalculatedBattleStrategy;

public class TestEpsilonCivFactory implements GameFactory {
    EpsilonCivFactory epsilonCivFactory;
    DiceRollStrategy diceRollStrategy;

    public TestEpsilonCivFactory() {
        this.epsilonCivFactory = new EpsilonCivFactory();
        this.diceRollStrategy = new NoDiceRollStrategy();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return epsilonCivFactory.createWinnerStrategy();
    }

    @Override
    public TimeStrategy createTimeStrategy() {
        return epsilonCivFactory.createTimeStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return epsilonCivFactory.createUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return epsilonCivFactory.createWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new CalculatedBattleStrategy(diceRollStrategy);
    }

    public DiceRollStrategy getDice(){return diceRollStrategy;}
}
