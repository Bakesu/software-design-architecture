package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.variants.factories.AlphaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class GameObserverTest {
private Game game;
private StubGameObserver gameObserver;

    @BeforeEach
    public void setUp(){
        game = new GameImpl(new AlphaCivFactory());
        gameObserver = new StubGameObserver();
        game.addObserver(gameObserver);
    }

    @Test
    void moveUnit() {
        game.moveUnit(new Position(2,0), new Position(3,0));
        assertThat(gameObserver.worldChangedAt, is(2)); //Two tiles are updated
    }

    @Test
    void endOfTurn() {
        game.endOfTurn();
        assertThat(gameObserver.turnEnds, is(1));
    }

    @Test
    void changeWorkForceFocusInCityAt() {
        game.changeWorkForceFocusInCityAt(new Position(1,1), GameConstants.foodFocus);
        assertThat(gameObserver.worldChangedAt, is(1));
    }

    @Test
    void performUnitActionAt() {
        game.performUnitActionAt(new Position(4,3));
        assertThat(gameObserver.worldChangedAt, is(1));
    }

    @Test
    void setTileFocus() {
        game.setTileFocus(new Position(1,1));
        assertThat(gameObserver.tileFocusChangedAt, is(1));
    }

    public class StubGameObserver implements GameObserver {
        private int worldChangedAt = 0;
        private int turnEnds = 0;
        private int tileFocusChangedAt = 0;

        @Override
        public void worldChangedAt(Position pos) {
            System.out.println("World changed at " + pos);
            worldChangedAt++;
        }

        @Override
        public void turnEnds(Player nextPlayer, int age) {
            System.out.println("Turn ended");
            turnEnds++;
        }

        @Override
        public void tileFocusChangedAt(Position position) {
            System.out.println("Tile focus changed");
            tileFocusChangedAt++;
        }
    }
}