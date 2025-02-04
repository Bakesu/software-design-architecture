package hotciv.standard.variants.factories;

import hotciv.framework.GameConstants;
import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.standard.variants.strategies.*;

public class SemiCivFactory implements GameFactory {
    @Override
    public WinnerStrategy createWinnerStrategy() {
        return new ThreeStrikesWinnerStrategy();
    }

    @Override
    public TimeStrategy createTimeStrategy() {
        return new VaryingTimeStrategy();
    }

    @Override
    public UnitActionStrategy createUnitActionStrategy() {
        return new SettleAndFortifyUnitActionStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new CustomizableWorldLayoutStrategy();
    }

    @Override
    public BattleStrategy createBattleStrategy() {
        return new CalculatedBattleStrategy(new D6DiceRollStrategy());
    }
}
