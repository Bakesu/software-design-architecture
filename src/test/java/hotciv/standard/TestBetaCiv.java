package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.variants.factories.BetaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TestBetaCiv {
    GameImpl game;
    Position redArcherStartingPosition;
    Position blueCityPosition;
    @BeforeEach
    void setUp() {
        game = new GameImpl(new BetaCivFactory());
        redArcherStartingPosition = new Position(2,0);
        blueCityPosition = new Position(4,1);
    }

    @Test
    void AgeIncreasesAccordingToBetaCiv() {
        assertThat(game.getAge(), is(-4000));
        // From -4000 to year 1971 in 97 turns
        fastForwardXRounds(97);
        assertThat(game.getAge(), is(1971));
    }

    @Test
    public void redWinsByConquest(){
        game.moveUnit(redArcherStartingPosition, new Position(3,1));
        fastForwardXRounds(1);
        assertThat(game.getWinner(), is(nullValue()));
        game.moveUnit(new Position(3,1), blueCityPosition);
        assertThat(game.getWinner(), is(Player.RED));
    }

    /** HELP METHODS */

    public void fastForwardXRounds(int numberOfRounds)  {
        for (int i = 0; i < numberOfRounds * 2; i++) {
            game.endOfTurn();
        }
    }
}