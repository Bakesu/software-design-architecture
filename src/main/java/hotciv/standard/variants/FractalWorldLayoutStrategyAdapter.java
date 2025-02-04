package hotciv.standard.variants;

import hotciv.framework.GameConstants;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;
import thirdparty.ThirdPartyFractalGenerator;
import hotciv.framework.Game;
import hotciv.framework.variants.strategies.WorldLayoutStrategy;

public class FractalWorldLayoutStrategyAdapter implements WorldLayoutStrategy {
    private ThirdPartyFractalGenerator fractalGenerator;

    @Override
    public void defineWorld(Game game) {
        String[] arrayLayout = new String[GameConstants.WORLDSIZE];
        fractalGenerator = new ThirdPartyFractalGenerator();
        String line;
        //System.out.println("Demonstration of the fractal landscape generator");
        for ( int r = 0; r < 16; r++ ) {
            line = "";
            for (int c = 0; c < 16; c++) {
                line = line + fractalGenerator.getLandscapeAt(r, c);
            }
            //System.out.println(line);
            arrayLayout[r] = line;
        }
        GameImpl g = (GameImpl) game;
        Utility.layoutToTileConvert(g, arrayLayout);
    }
}
