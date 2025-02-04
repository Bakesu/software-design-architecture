package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.testFactories.TestFractalGameFactory;
import hotciv.standard.variants.FractalWorldLayoutStrategyAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestFractalCiv {

    private FractalWorldLayoutStrategyAdapter fractalGenerator;
    private Game game;
    private Position tileAtZeroZero;

    @BeforeEach
    void setUp() {
    tileAtZeroZero = new Position(0,0);
    }


    @Test
    void testWorld() {
        assertFalse(tilesAreTheSameIn25Games());
    }

    private boolean tilesAreTheSameIn25Games(){
        String firstTile = null;
        for (int i = 0; i < 25; i++) {
            game = new GameImpl(new TestFractalGameFactory());
            String newestTile = game.getTileAt(tileAtZeroZero).getTypeString();

            if(firstTile == null) {
                firstTile = game.getTileAt(tileAtZeroZero).getTypeString();
            } else if (!firstTile.equals(newestTile)){
                return false;
            }
        }
        return true;
    }
}