package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.variants.factories.GammaCivFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGammaCiv {
    private Position redSettlerStartingPosition;
    private Position redArcherStartingPosition;
    private GameImpl game;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new GammaCivFactory());
        redSettlerStartingPosition = new Position(4,3);
        redArcherStartingPosition = new Position(2,0);
    }

    @Test
    void redSettlerCanBuildRedCity() {
        assertThat(game.getCityAt(redSettlerStartingPosition), is(nullValue()));
        game.performUnitActionAt(redSettlerStartingPosition);
        assertThat(game.getCityAt(redSettlerStartingPosition).getOwner(), is(Player.RED));
    }

    @Test
    void redSettlerIsConsumedWhenBuildingCity(){
        game.performUnitActionAt(redSettlerStartingPosition);
        assertThat(game.getUnitAt(redSettlerStartingPosition), is(nullValue()));
    }

    @Test
    void unitActionOnlyAllowedOnOwnTurn(){
        game.endOfTurn();
        game.performUnitActionAt(redArcherStartingPosition);
        //Defensive stats are not doubled
        assertThat(game.getUnitAt(redArcherStartingPosition).getDefensiveStrength(), is(GameConstants.ARCHER_DEFENSE));
    }

    @Test
    void redArcherDoubleDefensiveStrengthWhenFortified(){
        game.performUnitActionAt(redArcherStartingPosition);
        assertThat(game.getUnitAt(redArcherStartingPosition).getDefensiveStrength(), is(GameConstants.ARCHER_DEFENSE*2));
    }

    @Test
    void redArcherHasNormalStatsWhenUnfortifying(){
        game.performUnitActionAt(redArcherStartingPosition);
        game.performUnitActionAt(redArcherStartingPosition);
        assertThat(game.getUnitAt(redArcherStartingPosition).getDefensiveStrength(), is(GameConstants.ARCHER_DEFENSE));
    }

    @Test
    void redArcherCannotMoveWhileFortified(){
        game.performUnitActionAt(redArcherStartingPosition);
        assertFalse(game.moveUnit(redArcherStartingPosition, new Position(3,0)));
        game.performUnitActionAt(redArcherStartingPosition);
        assertTrue(game.moveUnit(redArcherStartingPosition, new Position(3,0)));
    }
}