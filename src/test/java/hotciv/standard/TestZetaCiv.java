package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.variants.factories.ZetaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
class TestZetaCiv {
    private GameImpl game;
    private Position redArcherStartingPosition;
    private Position blueCityPosition;
    private Position redLegion1Position;
    private Position redLegion2Position;
    private Position redLegion3Position;
    private Position blueLegion1Position;
    private Position blueLegion2Position;
    private Position blueLegion3Position;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new ZetaCivFactory());

        redArcherStartingPosition = new Position(2,0);
        blueCityPosition = new Position(4,1);

        redLegion1Position = new Position(9,10);
        redLegion2Position = new Position(10,10);
        redLegion3Position = new Position(11,10);

        blueLegion1Position = new Position(9,11);
        blueLegion2Position = new Position(10,11);
        blueLegion3Position = new Position(11,11);

        game.createUnitAt(redLegion1Position, Player.RED, GameConstants.LEGION);
        game.createUnitAt(redLegion2Position, Player.RED, GameConstants.LEGION);
        game.createUnitAt(redLegion3Position, Player.RED, GameConstants.LEGION);

        game.createUnitAt(blueLegion1Position, Player.BLUE, GameConstants.LEGION);
        game.createUnitAt(blueLegion2Position, Player.BLUE, GameConstants.LEGION);
        game.createUnitAt(blueLegion3Position, Player.BLUE, GameConstants.LEGION);

    }

    @Test
    public void shouldUseCityConquestStrategyInTheFirst20Rounds() {
        game.moveUnit(redArcherStartingPosition, new Position(3,1));
        fastForwardXRounds(1);
        game.moveUnit(new Position(3,1),blueCityPosition);
        assertThat(game.getWinner(), is(Player.RED));

    }

    @Test
    public void shouldUseThreeStrikesStrategyAfterRound20() {
        fastForwardXRounds(20);

        assertThat(game.getWinner(), is(nullValue()));

        game.moveUnit(redLegion1Position, blueLegion1Position);
        game.moveUnit(redLegion2Position, blueLegion2Position);
        game.moveUnit(redLegion3Position, blueLegion3Position);

        assertThat(game.getWinner(), is(Player.RED));

    }

    @Test
    public void battlesWonResetsAfter20Rounds() {

        game.moveUnit(redLegion1Position, blueLegion1Position);
        game.moveUnit(redLegion2Position, blueLegion2Position);
        game.moveUnit(redLegion3Position, blueLegion3Position);

        assertThat(game.getWinner(), is(nullValue()));

        fastForwardXRounds(20);

        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldDisableCityConquestAfterRound20() {
        game.moveUnit(redArcherStartingPosition, new Position(3,1));

        fastForwardXRounds(20);

        game.moveUnit(new Position(3,1),blueCityPosition);

        assertThat(game.getWinner(), is(nullValue()));
    }



    /** HELP METHODS */

    public void fastForwardXRounds(int numberOfRounds)  {
        for (int i = 0; i < numberOfRounds * 2; i++) {
            game.endOfTurn();
        }
    }
}