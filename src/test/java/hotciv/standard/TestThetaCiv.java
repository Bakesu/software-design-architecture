package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.variants.factories.ThetaCivFactory;
import hotciv.standard.variants.factories.ZetaCivFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestThetaCiv {
    private GameImpl game;
    private City blueCity;
    private Position blueCityPosition;
    private Unit caravan;
    private Position desertTilePosition;

    @BeforeEach
    void setUp() {
        game = new GameImpl(new ThetaCivFactory());
        blueCityPosition = new Position(4, 5);
        blueCity = game.getCityAt(blueCityPosition);
        caravan = game.getUnitAt(new Position(9,6));
        desertTilePosition = new Position(6,5);
    }

    @Test
    void desertTileAt(){
        assertThat(game.getTileAt(new Position(0,7)).getTypeString(), is(GameConstants.DESERT));
        assertThat(game.getTileAt(new Position(7,4)).getTypeString(), is(GameConstants.DESERT));
        assertThat(game.getTileAt(new Position( 11,6)).getTypeString(), is(GameConstants.DESERT));
    }

    @Test
    void caravanAtNineSix(){
        assertThat(caravan.getTypeString(), is(GameConstants.CARAVAN));
    }

    @Test
    void nonCaravanUnitsCannotMoveOnDesert() {
        game.createUnitAt(new Position(5,4), Player.RED, GameConstants.ARCHER);
        game.createUnitAt(new Position(5,6), Player.RED, GameConstants.LEGION);

        assertFalse(game.moveUnit(new Position(5,5), desertTilePosition));
        assertFalse(game.moveUnit(new Position(5,4), desertTilePosition));
        assertFalse(game.moveUnit(new Position(5,6), desertTilePosition));
    }

    @Test
    void caravanCanMoveOnDesertTiles(){
        game.createUnitAt(new Position(5,6), Player.RED, GameConstants.CARAVAN);

        assertTrue(game.moveUnit(new Position(5,6), desertTilePosition));
    }

    @Test
    void caravanCanMoveTwiceInARound(){
        game.createUnitAt(new Position(5,6), Player.RED, GameConstants.CARAVAN);

        assertTrue(game.moveUnit(new Position(5,6), desertTilePosition));
        assertTrue(game.moveUnit(desertTilePosition, new Position(7,5)));
    }

    @Test
    void cityCanProduceCaravan(){
        game.endOfTurn();
        game.changeProductionInCityAt(blueCityPosition, GameConstants.CARAVAN);
        assertThat(blueCity.getProduction(), is(GameConstants.CARAVAN));
    }

    @Test
    void caravansCanBePlacedFor30Treasury(){
        game.endOfTurn();
        game.changeProductionInCityAt(blueCityPosition, GameConstants.CARAVAN);

        fastForwardXRounds(5);
        game.endOfTurn(); //The round has now ended 6 times with 6 production each round, thus 36 production would be enough to produce a caravan and leave the city with 6.
        assertThat(blueCity.getTreasury(), is(6));
        assertThat(game.getUnitAt(blueCityPosition).getTypeString(), is(GameConstants.CARAVAN));
    }

    @Test
    void caravansHave1AttackAnd4Defense(){
        assertThat(caravan.getAttackingStrength(), is(1));
        assertThat(caravan.getDefensiveStrength(), is(4));
    }

    @Test
    void caravanCanPopulateCity(){
        assertThat(blueCity.getSize(), is(1));
        game.createUnitAt(blueCityPosition, Player.BLUE, GameConstants.CARAVAN);
        game.endOfTurn();
        game.performUnitActionAt(blueCityPosition);
        assertThat(blueCity.getSize(), is(2));
    }

    @Test
    void redSettlerCanBuildRedCity() {
        assertThat(game.getCityAt(new Position(5,5)), is(nullValue()));
        game.performUnitActionAt(new Position(5,5));
        assertThat(game.getCityAt(new Position(5,5)).getOwner(), is(Player.RED));
    }

    /* HELP METHODS */

    public void fastForwardXRounds(int numberOfRounds)  {
        for (int i = 0; i < numberOfRounds * 2; i++) {
            game.endOfTurn();
        }
    }
}