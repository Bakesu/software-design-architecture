package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.testFactories.TestEpsilonCivFactory;
import hotciv.standard.stubs.NoDiceRollStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestEpsilonCiv {
    private GameImpl game;
    private Position redArcherStartingPosition;
    private Position blueLegionStartingPosition;
    private Position blueCityPosition;
    private NoDiceRollStrategy dice;

    @BeforeEach
    void setUp() {
        TestEpsilonCivFactory testEpsilonCivFactory = new TestEpsilonCivFactory();
        game = new GameImpl(testEpsilonCivFactory);
        dice = (NoDiceRollStrategy) testEpsilonCivFactory.getDice();

        redArcherStartingPosition = new Position(2,0);
        blueLegionStartingPosition = new Position(3,2);
        blueCityPosition = new Position(4,1);
    }

    @Test
    public void redArcherWinsOverLegionByStandingOnAHill(){
        //Creating a hill for battle testing
        game.createTileAt(new Position(2,1), GameConstants.HILLS);
        game.moveUnit(redArcherStartingPosition, new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        // Archer should win with an attack strength of 2*2 against the legions' 2 defense.
        assertTrue(game.moveUnit(new Position(2,1), new Position(3,2)));
    }

    @Test
    public void archerGainsStatsFromAdjacentUnits() {
        game.createUnitAt(new Position(3,1), Player.BLUE,GameConstants.ARCHER);
        game.createUnitAt(new Position(3,0), Player.BLUE,GameConstants.SETTLER);
        game.endOfTurn();
        // Blue archer should win with an attack of 2+1+1 against the redArchers' 3 defense.
        assertTrue(game.moveUnit(new Position(3,1), redArcherStartingPosition));
    }

    @Test
    public void legionGainsStatsFromStandingInCity() {
        game.createUnitAt(new Position(3,0), Player.RED, GameConstants.LEGION);
        game.endOfTurn();
        game.moveUnit(blueLegionStartingPosition, blueCityPosition);
        game.endOfTurn();
        //Archers' attack should fail as it is 2+1 attack against 2*3 defense.
        assertTrue(game.moveUnit(new Position(3,0), blueCityPosition));
        // The attacking archer should be removed
        assertThat(game.getUnitAt(new Position(3,0)), is(nullValue()));
    }

    @Test
    public void redShouldWinOnThreeSuccessfulStrikes(){

        Position redLegion1Position = new Position(9,10);
        Position redLegion2Position = new Position(10,10);
        Position redLegion3Position = new Position(11,10);

        Position blueLegion1Position = new Position(9,11);
        Position blueLegion2Position = new Position(10,11);
        Position blueLegion3Position = new Position(11,11);

        assertThat(game.getWinner(), is(nullValue()));

        game.createUnitAt(redLegion1Position, Player.RED, GameConstants.LEGION);
        game.createUnitAt(redLegion2Position, Player.RED, GameConstants.LEGION);
        game.createUnitAt(redLegion3Position, Player.RED, GameConstants.LEGION);

        game.createUnitAt(blueLegion1Position, Player.BLUE, GameConstants.LEGION);
        game.createUnitAt(blueLegion2Position, Player.BLUE, GameConstants.LEGION);
        game.createUnitAt(blueLegion3Position, Player.BLUE, GameConstants.LEGION);

        game.moveUnit(redLegion1Position, blueLegion1Position);
        game.moveUnit(redLegion2Position, blueLegion2Position);
        game.moveUnit(redLegion3Position, blueLegion3Position);

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void attackingUnitDiesInATie(){
        game.endOfTurn();
        game.moveUnit(blueLegionStartingPosition, new Position(3,1));
        game.endOfTurn();

        assertTrue(game.moveUnit(redArcherStartingPosition, new Position(3,1)));
        assertThat(game.getUnitAt(redArcherStartingPosition), is(nullValue()));
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void defendingArcherWinsOverLegionWhenItRollsWell(){
        game.moveUnit(redArcherStartingPosition, new Position(3,1));
        game.endOfTurn();

        //Blue legion would normally win, but archer rolls 6:
        dice.setAttackerDice(1); //4*1 = 4
        dice.setDefenderDice(6); //3*6 = 18

        assertTrue(game.moveUnit(blueLegionStartingPosition, new Position(3,1)));
        assertThat(game.getUnitAt(blueLegionStartingPosition), is(nullValue()));
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void attackingArcherWinsOverLegionWhenItRollsWell(){
        game.endOfTurn();
        game.moveUnit(blueLegionStartingPosition, new Position(3,1));
        game.endOfTurn();

        //The attacking archer would normally die in a tie, but rolls better:
        dice.setAttackerDice(6); //2*6 = 12
        dice.setDefenderDice(1); //2*1 = 2

        assertTrue(game.moveUnit(redArcherStartingPosition, new Position(3,1)));
        assertThat(game.getUnitAt(redArcherStartingPosition), is(nullValue()));
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(), is(Player.RED));
    }

}