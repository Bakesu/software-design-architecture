package hotciv.standard.variants.strategies;

import hotciv.framework.*;
import hotciv.framework.variants.strategies.WorldLayoutStrategy;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;

public class CustomizableWorldLayoutStrategy implements WorldLayoutStrategy {
    String[] layout;
    @Override
    public void defineWorld(Game game) {
        GameImpl g = (GameImpl) game;

        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
                layout = new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        Utility.layoutToTileConvert(g, layout);
        g.createCityAt(new Position(8,12), Player.RED);
        g.createCityAt(new Position(4,5), Player.BLUE);
        g.createUnitAt(new Position(5,5), Player.RED, GameConstants.SETTLER);
        g.createUnitAt(new Position(4,4), Player.RED, GameConstants.LEGION);
        g.createUnitAt(new Position(3,8), Player.RED, GameConstants.ARCHER);
    }
}
