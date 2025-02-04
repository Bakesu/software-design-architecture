package hotciv.standard.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.variants.strategies.UnitActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

public class CaravanDecoratorUnitActionStrategy implements UnitActionStrategy {
    UnitActionStrategy unitActionStrategy = new SettleAndFortifyUnitActionStrategy();

    @Override
    public void performUnitActionAt(Position unitPosition, Game game) {
        GameImpl g = (GameImpl) game;
        boolean isCaravan = game.getUnitAt(unitPosition).getTypeString().equals(GameConstants.CARAVAN);

        if (!isCaravan) {
            unitActionStrategy.performUnitActionAt(unitPosition, game);
        } else {
            boolean isInCity = game.getCityAt(unitPosition) != null;

            if (!isInCity) {
                return;
            } else {
                populate(unitPosition, g);
            }
        }
    }

    private void populate(Position unitPosition, GameImpl g) {
        CityImpl city = (CityImpl) g.getCityAt(unitPosition);
        g.removeUnitAt(unitPosition);
        city.increasePopulation();
    }
}