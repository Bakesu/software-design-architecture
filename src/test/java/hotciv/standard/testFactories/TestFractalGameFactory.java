package hotciv.standard.testFactories;

import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.stubs.NoDiceRollStrategy;
import hotciv.standard.variants.FractalWorldLayoutStrategyAdapter;
import hotciv.standard.variants.factories.AlphaCivFactory;
import hotciv.standard.variants.factories.EpsilonCivFactory;
import hotciv.standard.variants.strategies.CalculatedBattleStrategy;

public class TestFractalGameFactory implements GameFactory {
    AlphaCivFactory alphaCivFactory;

    public TestFractalGameFactory() {
        this.alphaCivFactory = new AlphaCivFactory();
    }

    @Override
    public WinnerStrategy createWinnerStrategy() {
        return alphaCivFactory.createWinnerStrategy();
    }

    @Override
    public TimeStrategy createTimeStrategy() {
        return alphaCivFactory.createTimeStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return alphaCivFactory.createUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new FractalWorldLayoutStrategyAdapter();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return alphaCivFactory.createBattleStrategy();
    }

}
