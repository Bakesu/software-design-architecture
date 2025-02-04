package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.variants.factories.AlphaCivFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Skeleton class for AlphaCiv test cases
 * <p>
 * Updated Aug 2020 for JUnit 5 includes
 * Updated Oct 2015 for using Hamcrest matchers
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TestAlphaCiv {
    private Game game;
    private City redCity;
    private City blueCity;
    private Unit redArcher;
    private Unit blueLegion;
    private Unit redSettler;
    private Position redArcherStartingPosition;
    private Position blueLegionStartingPosition;
    private Position redSettlerStartingPosition;
    private Position redCityPosition;
    private Position blueCityPosition;
    private Tile oceanTile;
    private Tile mountainTile;
    private Tile hillTile;
    /*
     * Fixture for alphaciv testing.
     */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaCivFactory());
        //Cities
        redCity = game.getCityAt(new Position(1,1));
        blueCity = game.getCityAt(new Position(4,1));
        //Units
        redArcher = game.getUnitAt(new Position(2,0));
        blueLegion = game.getUnitAt(new Position(3,2));
        redSettler = game.getUnitAt(new Position(4,3));
        //Unit starting positions
        redArcherStartingPosition = new Position(2,0);
        blueLegionStartingPosition = new Position(3,2);
        redSettlerStartingPosition = new Position(4,3);
        redCityPosition = new Position(1,1);
        blueCityPosition = new Position(4,1);
        // Tiles
        oceanTile = game.getTileAt(new Position(1,0));
        mountainTile = game.getTileAt(new Position(2,2));
        hillTile = game.getTileAt(new Position(0,1));
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    //Triangulating the first faked solution
    @Test
    public void shouldBeBlueAsSecondPlayer() {
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedAfterAllPlayersTurns() {
        fastForwardXRounds(1);
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void redCityAtOneOne() {
        assertThat(redCity.getOwner(), is(Player.RED));
    }

    @Test
    public void blueCityAtFourOne() {
        assertThat(blueCity.getOwner(), is(Player.BLUE));
    }

    @Test
    public void mostlyPlainsTerrain() {
        assertThat(game.getTileAt(new Position(0, 0)).getTypeString(), is(GameConstants.PLAINS));
        assertThat(game.getTileAt(new Position(15, 15)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void oceanAtOneZero() {
        assertThat(oceanTile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void hillsAtZeroOne() {
        assertThat(hillTile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void mountainsAtTwoTwo() {
        assertThat(mountainTile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void redArcherAtTwoZero() {
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(redArcherStartingPosition).getOwner(), is(Player.RED));
    }

    @Test
    public void blueLegionAtThreeTwo() {
        assertThat(blueLegion.getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(blueLegionStartingPosition).getOwner(), is(Player.BLUE));
    }

    @Test
    public void redSettlerAtFourThree() {
        assertThat(redSettler.getTypeString(), is(GameConstants.SETTLER));
        assertThat(game.getUnitAt(redSettlerStartingPosition).getOwner(), is(Player.RED));
    }

    @Test
    public void redArcherCanMove() {
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
        assertTrue(game.moveUnit(redArcherStartingPosition, new Position(2, 1)));
        //old position is now empty:
        assertThat(game.getUnitAt(redArcherStartingPosition), is(nullValue()));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void cannotMoveMoreThanOneTile() {
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
        assertFalse(game.moveUnit(redArcherStartingPosition, new Position(4, 0)));
        assertThat(game.getUnitAt(new Position(4, 0)), is(nullValue()));
    }

    @Test
    public void shouldResetUnitMoveCountInNewRound(){
        game.endOfTurn();
        game.moveUnit(blueLegionStartingPosition, new Position(3,3));
        fastForwardXRounds(1);
        assertThat(game.getUnitAt(new Position(3,3)).getMoveCount(), is(1));
    }

    @Test
    public void unitShouldRetainMoveCountWhenNotChangingPosition() {
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
        assertFalse(game.moveUnit(redArcherStartingPosition, redArcherStartingPosition));
        assertThat(redArcher.getMoveCount(), is(1));
    }

    @Test
    public void archerCannotMoveOverOcean() {
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
        assertFalse(game.moveUnit(redArcherStartingPosition, new Position(1, 0)));
        assertThat(redArcher.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void legionCannotMoveOverMountain() {
        assertThat(blueLegion.getTypeString(), is(GameConstants.LEGION));
        assertFalse(game.moveUnit(blueLegionStartingPosition, new Position(2, 2)));
        assertThat(blueLegion.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void redCannotMoveBluesUnits() {
        assertFalse(game.moveUnit(blueLegionStartingPosition, new Position(3, 3)));
        game.endOfTurn();
        assertTrue(game.moveUnit(blueLegionStartingPosition, new Position(3, 3)));
    }

    @Test
    public void blueCanMoveInSecondTurn() {
        game.endOfTurn();
        assertTrue(game.moveUnit(blueLegionStartingPosition, new Position(3, 3)));
    }

    @Test
    public void blueAttackingUnitShouldWin() {
        game.moveUnit(redArcherStartingPosition, new Position(2, 1));
        game.endOfTurn();
        assertTrue(game.moveUnit(blueLegionStartingPosition, new Position(2, 1)));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(2, 1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void redArcherOnlyHasOneMovePerTurn() {
        assertTrue(game.moveUnit(redArcherStartingPosition, new Position(3, 0)));
        assertFalse(game.moveUnit(new Position(3, 0), new Position(4, 0)));
        game.endOfTurn();
        game.endOfTurn();
        assertTrue(game.moveUnit(new Position(3, 0), new Position(4, 0)));
        assertFalse(game.moveUnit(new Position(4, 0), new Position(5, 0)));
    }

    @Test
    public void redWinsIn3000BC() {
        assertThat(game.getWinner(), is(nullValue()));
        assertThat(game.getAge(), is(-4000));
        fastForwardXRounds(10);
        assertThat(game.getAge(), is(-3000));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldStoreSixTreasuryPerRound() {
        assertThat(redCity.getTreasury(), is(0));
        fastForwardXRounds(1);
        assertThat(redCity.getTreasury(), is(6));
        fastForwardXRounds(1);
        assertThat(redCity.getTreasury(), is(12));
    }

    @Test
    public void blueCityCanProduceSettler() {
        //Now testing legal production selection
        game.endOfTurn();
        game.changeProductionInCityAt(blueCityPosition, GameConstants.SETTLER);
        assertThat(blueCity.getProduction(), is(GameConstants.SETTLER));
        fastForwardXRounds(6);
        assertThat(game.getCityAt(blueCityPosition).getTreasury(), is(6));
        assertThat(game.getUnitAt(blueCityPosition).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void blueCityCanProduceLegion() {
        //Now testing legal production selection
        game.endOfTurn();
        game.changeProductionInCityAt(blueCityPosition, GameConstants.LEGION);
        assertThat(blueCity.getProduction(), is(GameConstants.LEGION));
        fastForwardXRounds(4);
        assertThat(game.getCityAt(blueCityPosition).getTreasury(), is(9));
        assertThat(game.getUnitAt(blueCityPosition).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void cannotSetProductionInOpponentTurn() {
        //Testing for setting production while its the other player's turn.
        game.changeProductionInCityAt(blueCityPosition, GameConstants.SETTLER);
        assertThat(blueCity.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void blueCityCanChangeWorkforceFocus() {
        //Testing for setting of workForceFocus while it's the other player's turn.
        game.changeWorkForceFocusInCityAt(blueCityPosition, GameConstants.productionFocus);
        assertThat(blueCity.getWorkforceFocus(), is(GameConstants.foodFocus));
        //Now testing legal workForceFocus
        game.endOfTurn();
        game.changeWorkForceFocusInCityAt(blueCityPosition, GameConstants.productionFocus);
        assertThat(blueCity.getWorkforceFocus(), is(GameConstants.productionFocus));
    }

    @Test
    public void citySizeIsAlwaysOne() {
        assertThat(redCity.getSize(), is(1));
        assertThat(blueCity.getSize(), is(1));
    }

    @Test
    public void shouldPlaceNewUnitsClockwise(){
        game.changeProductionInCityAt(redCityPosition, GameConstants.ARCHER);
        fastForwardXRounds(11);
        //Checking start and end position
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(0,0)).getTypeString(), is(GameConstants.ARCHER));
        //Covering a situation where all positions are full
        fastForwardXRounds(2);
    }

    @Test
    public void shouldDecreaseTreasuryWhenProducingUnit(){
        game.changeProductionInCityAt(redCityPosition, GameConstants.ARCHER);
        fastForwardXRounds(3);
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(redCity.getTreasury(), is(8));
    }

    @Test
    public void shouldNotSpawnUnitsOnIllegalTerrain(){
        game.changeProductionInCityAt(redCityPosition, GameConstants.ARCHER);
        fastForwardXRounds(11);
        assertThat(game.getUnitAt(new Position(2,2)), is(nullValue())); //Mountain tile in the way
        assertThat(game.getUnitAt(new Position(1,0)), is(nullValue())); //Oceans in the way
    }

    @Test
    public void shouldNotSpawnUnitsOnOthers(){
        game.changeProductionInCityAt(redCityPosition, GameConstants.ARCHER);
        fastForwardXRounds(11);
        assertThat(game.getUnitAt(new Position(0,0)).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void unitsShouldNotTeamkill() {
        game.endOfTurn();
        game.changeProductionInCityAt(blueCityPosition, GameConstants.ARCHER);
        fastForwardXRounds(3);
        assertFalse(game.moveUnit(new Position(4,1),new Position(3,2)));
    }

    @Test
    public void redArcherCanConquerBlueCity(){
        game.moveUnit(redArcherStartingPosition, new Position(3,1));
        fastForwardXRounds(1);
        game.moveUnit(new Position(3,1), blueCityPosition);
        assertThat(blueCity.getOwner(), is(Player.RED));
    }

    @Test
    public void archerShouldHave3Defense2Attack() {
        assertThat(redArcher.getDefensiveStrength(), is(GameConstants.ARCHER_DEFENSE));
        assertThat(redArcher.getAttackingStrength(), is(GameConstants.ARCHER_ATTACK));
    }

    @Test
    public void legionShouldHave2Defense4Attack() {
        assertThat(blueLegion.getDefensiveStrength(), is(GameConstants.LEGION_DEFENSE));
        assertThat(blueLegion.getAttackingStrength(), is(GameConstants.LEGION_ATTACK));
    }

    @Test
    public void settlerShouldHave3Defense0Attack() {
        assertThat(redSettler.getDefensiveStrength(), is(GameConstants.SETTLER_DEFENSE));
        assertThat(redSettler.getAttackingStrength(), is(GameConstants.SETTLER_ATTACK));
    }

    public void fastForwardXRounds(int numberOfRounds)  {
        for (int i = 0; i < numberOfRounds * 2; i++) {
            game.endOfTurn();
        }
    }
}
