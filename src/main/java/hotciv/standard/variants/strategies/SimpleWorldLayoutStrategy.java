package hotciv.standard.variants.strategies;

import hotciv.framework.*;
import hotciv.framework.variants.strategies.WorldLayoutStrategy;
import hotciv.standard.GameImpl;

public class SimpleWorldLayoutStrategy implements WorldLayoutStrategy {


    public void defineWorld(Game game){
        GameImpl g = (GameImpl) game;
        //Setting tile terrain types
        g.createTileAt(new Position(1,0), GameConstants.OCEANS);
        g.createTileAt(new Position(0,1), GameConstants.HILLS);
        g.createTileAt(new Position(2,2), GameConstants.MOUNTAINS);
        //Setting cities
        g.createCityAt(new Position(1,1), Player.RED);
        g.createCityAt(new Position(4,1), Player.BLUE);
        //Setting units
        g.createUnitAt(new Position(2,0), Player.RED, GameConstants.ARCHER);
        g.createUnitAt(new Position(3,2), Player.BLUE, GameConstants.LEGION);
        g.createUnitAt(new Position(4,3), Player.RED, GameConstants.SETTLER);
    }
}

