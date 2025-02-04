package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.variants.GameLogDecorator;
import hotciv.standard.variants.factories.AlphaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTournamentCiv {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new GameLogDecorator(new AlphaCivFactory());
    }

    /* FOR MANUAL TESTING */

    @Test
    void logDecoratorLogsMovementCorrectly(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
        game.endOfTurn();
        game.changeWorkForceFocusInCityAt(new Position(4,1), GameConstants.foodFocus);

        /**
         RED moves archer from [2,0] to [3,1].
         RED changes production in city at [1,1] to legion.
         RED ends turn.
         BLUE changes work force focus in city at [4,1] to food focus.
         */
    }

    @Test
    void illegalMoveIsNotLogged() {
        game.moveUnit(new Position(2,0), new Position(4,0));
    }
}