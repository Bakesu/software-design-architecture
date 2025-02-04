package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.testFactories.TestSemiCivFactory;
import hotciv.standard.stubs.NoDiceRollStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSemiCiv {
    private GameImpl game;
    private NoDiceRollStrategy dice;

    @BeforeEach
    void setUp() {
        TestSemiCivFactory testSemiCivFactory = new TestSemiCivFactory();
        game = new GameImpl(testSemiCivFactory);
        dice = (NoDiceRollStrategy) testSemiCivFactory.getDice();

        game.createUnitAt(new Position(9,4), Player.RED, GameConstants.ARCHER);
        game.createUnitAt(new Position(9,5), Player.RED, GameConstants.ARCHER);
        game.createUnitAt(new Position(9,6), Player.RED, GameConstants.ARCHER);

        game.createUnitAt(new Position(10,4), Player.BLUE, GameConstants.ARCHER);
        game.createUnitAt(new Position(10,5), Player.BLUE, GameConstants.ARCHER);
        game.createUnitAt(new Position(10,6), Player.BLUE, GameConstants.ARCHER);
    }

    @Test
    void strategiesShouldWorkTogether(){
        //Unit action and world layout strategy
        game.performUnitActionAt(new Position(5,5));
        assertThat(game.getCityAt(new Position(5,5)).getOwner(), is(Player.RED));

        //Time strategy
        fastForwardXRounds(40);
        assertThat(game.getAge(), is(-1));

        //Winner strategy & Battle strategy
        assertThat(game.getWinner(), is(nullValue()));
        dice.setAttackerDice(6);
        dice.setDefenderDice(1);
        game.moveUnit(new Position(9,4), new Position(10,4));
        game.moveUnit(new Position(9,5), new Position(10,5));
        game.moveUnit(new Position(9,6), new Position(10,6));

        assertThat(game.getWinner(), is(Player.RED));
    }

    /* HELP METHODS */

    public void fastForwardXRounds(int numberOfRounds)  {
        for (int i = 0; i < numberOfRounds * 2; i++) {
            game.endOfTurn();
        }
    }
}