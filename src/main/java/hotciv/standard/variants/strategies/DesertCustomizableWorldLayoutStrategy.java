package hotciv.standard.variants.strategies;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.variants.strategies.WorldLayoutStrategy;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;

import javax.swing.text.Utilities;

public class DesertCustomizableWorldLayoutStrategy implements WorldLayoutStrategy {String[] layout;
    @Override
    public void defineWorld(Game game) {
        GameImpl g = (GameImpl) game;

        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        layout = new String[] {
                "...ooModdoo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...odd..........",
                ".ooodo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoddo.offoooo",
                "oodddodo...ooooo",
                ".ooMMMdooo......",
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
        g.createUnitAt(new Position(9,6), Player.BLUE, GameConstants.CARAVAN);
    }
}
