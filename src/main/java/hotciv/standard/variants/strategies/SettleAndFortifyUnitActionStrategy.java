package hotciv.standard.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.variants.strategies.UnitActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

public class SettleAndFortifyUnitActionStrategy implements UnitActionStrategy {
    @Override
    public void performUnitActionAt(Position unitPosition, Game game) {
        GameImpl g = (GameImpl) game;
        UnitImpl unit = (UnitImpl) game.getUnitAt(unitPosition);
        String type = unit.getTypeString();

        boolean playerInTurn = unit.getOwner().equals(game.getPlayerInTurn());

        if(!playerInTurn) return;

        switch (type) {
            case GameConstants.SETTLER:
                g.removeUnitAt(unitPosition);
                g.createCityAt(unitPosition, unit.getOwner());
                break;
            case GameConstants.ARCHER:
                if(unit.isFortified()){
                    unit.breakFortification();
                } else {
                    unit.fortify();
                }
                break;
            default:
                break;
        }
    }
}
