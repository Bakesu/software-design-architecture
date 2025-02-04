package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.variants.factories.GameFactory;
import hotciv.framework.variants.strategies.*;
import hotciv.view.CivDrawing;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Skeleton implementation of HotCiv.
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

public class GameImpl implements Game {
    // Factory
    private final GameFactory factory;
    // Strategies for different game-implementations
    private final TimeStrategy timeStrategy;
    private final WinnerStrategy winnerStrategy;
    private final UnitActionStrategy unitActionStrategy;
    private final WorldLayoutStrategy worldLayoutStrategy;
    private final BattleStrategy battleStrategy;
    // Game variables
    private int age = GameConstants.WORLD_STARTING_AGE;
    private int turnCounter = 0; //Holds an integer to keep count of whose turn it is
    private final HashMap<Position, City> cityPositions;
    private final HashMap<Position, Tile> tilePositions;
    private final HashMap<Position, Unit> unitPositions;
    private ArrayList<GameObserver> observerList;

    public GameImpl(GameFactory factory) {
        this.factory = factory;
        this.timeStrategy = factory.createTimeStrategy();
        this.winnerStrategy = factory.createWinnerStrategy();
        this.unitActionStrategy = factory.createUnitActionStrategy();
        this.worldLayoutStrategy = factory.createWorldLayoutStrategy();
        this.battleStrategy = factory.createBattleStrategy();

        cityPositions = new HashMap<>();
        tilePositions = new HashMap<>();
        unitPositions = new HashMap<>();
        worldLayoutStrategy.defineWorld(this);

        observerList = new ArrayList<>();
    }

    public Tile getTileAt(Position p) {
        return tilePositions.getOrDefault(p, new TileImpl(GameConstants.PLAINS)); //Any tile is plains unless it is specified in the tilePositions map.
    }

    public Unit getUnitAt(Position p) {
        return unitPositions.getOrDefault(p, null);
    }

    public City getCityAt(Position p) {
        return cityPositions.getOrDefault(p, null);
    }

    public Player getPlayerInTurn() {
        switch (turnCounter) {
            case 0:
                return Player.RED;
            case 1:
                return Player.BLUE;
        }
        return null;
    }

    public Player getWinner() {
        return winnerStrategy.getWinner(age, cityPositions);
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        if(!movementIsValid(from, to)) {
            notifyWorldChangedAt(from);
            notifyWorldChangedAt(to);
            return false;}

        UnitImpl unitToBeMoved = (UnitImpl) unitPositions.get(from);
        boolean noUnitAtTo = getUnitAt(to) == null;

        if(noUnitAtTo){
            changeUnitPosition(from, to, unitToBeMoved);
            conquerIfEnemyCityAt(to);
        } else if(battleStrategy.attackingUnitWins(this, from, to)) {
            changeUnitPosition(from, to, unitToBeMoved);
            winnerStrategy.incrementBattlesWonBy(getPlayerInTurn());
            conquerIfEnemyCityAt(to);
        } else {
            removeUnitAt(from);
        }
        unitToBeMoved.reduceMoveCount();
        notifyWorldChangedAt(from);
        notifyWorldChangedAt(to);
        return true;
    }

    public void endOfTurn() {
        turnCounter++;
        boolean endOfRound = getPlayerInTurn() == null;
        if (endOfRound) {
            resetMovementOfUnits();
            produceAndAddToTreasuryOfCities();
            incrementAge();
            winnerStrategy.incrementRoundsPlayed();
            turnCounter = 0;
        }
        notifyTurnEnds();
    }

    public void changeWorkForceFocusInCityAt(Position p, String focus) {
        CityImpl city = (CityImpl) cityPositions.get(p);
        if(getPlayerInTurn().equals(city.getOwner())) {
            city.setWorkFocus(focus);
        }
        notifyWorldChangedAt(p);
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl city = (CityImpl) cityPositions.get(p);
        if(getPlayerInTurn().equals(city.getOwner())){
            city.setProduction(unitType);
        }
        notifyWorldChangedAt(p);
    }

    public void performUnitActionAt(Position position) {
        unitActionStrategy.performUnitActionAt(position, this);
        notifyWorldChangedAt(position);
    }

    @Override
    public void addObserver(GameObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        notifyTileFocusChangedAt(position);
        return;
    }

    public void createCityAt(Position position, Player player){
        cityPositions.put(position, new CityImpl(player));
    }

    public void createUnitAt(Position position, Player owner, String type) {
        unitPositions.put(position, new UnitImpl(owner, type));
    }

    public void removeUnitAt(Position position){
        unitPositions.remove(position);
    }

    public void createTileAt(Position position, String type) {
        tilePositions.put(position, new TileImpl(type));
    }

    private void produceAndAddToTreasuryOfCities() {
        cityPositions.keySet()
                .stream()
                .filter(p -> cityPositions.get(p) != null)
                .forEach(p -> {
                    CityImpl city = (CityImpl) cityPositions.get(p);
                    boolean productionNotNull = city.getProduction() != null;

                    if(productionNotNull && city.hasEnoughTreasury()) {
                        Unit producedUnit = new UnitImpl(city.getOwner(), city.getProduction());
                        unitPositions.put(getAvailablePosition(p), producedUnit);
                        city.reduceCostOfProductionFromTreasury();
                    }
                    city.addToTreasury(6);
                });
    }

    private void resetMovementOfUnits() {
        unitPositions.keySet()
                .stream()
                .filter(p -> unitPositions.get(p) != null)
                .forEach(p -> {
                    UnitImpl unit = (UnitImpl) unitPositions.get(p);
                    unit.resetMoveCount();
                });
    }

    private Position getAvailablePosition (Position center) {
        int[] rowOrder = {0, -1, -1, 0, 1, 1, 1, 0, -1};
        int[] columnOrder = {0, 0, 1, 1, 1, 0, -1, -1, -1};

        Position checkingPosition;

        for(int i = 0; i < 9; i++) {

            int checkingRow = rowOrder[i] + center.getRow();
            int checkingColumn = columnOrder[i] + center.getColumn();

            checkingPosition = new Position(checkingRow, checkingColumn);

            if(isValidForUnitPlacement(checkingPosition, getCityAt(center).getProduction())) {
                return checkingPosition;
            }
        }
        return null;
    }

    private boolean isValidForUnitPlacement(Position p, String unitType) {
        return getUnitAt(p) == null
                && isTerrainValidForUnitPlacement(p, unitType);
    }

    private boolean isOceansOrMountainsTile(Position p){
        boolean tileIsOceans = getTileAt(p).getTypeString().equals(GameConstants.OCEANS);
        boolean tileIsMountains = getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS);

        return tileIsOceans || tileIsMountains;
    }

    private boolean isTerrainValidForUnitPlacement(Position p, String unitType){
        boolean isCaravan = unitType.equals(GameConstants.CARAVAN);
        boolean tileIsDesert = getTileAt(p).getTypeString().equals(GameConstants.DESERT);

        if(isCaravan) {
            return !isOceansOrMountainsTile(p);
        }

        return !tileIsDesert && !isOceansOrMountainsTile(p);
    }

    private boolean isFriendlyUnitInTheWay(Position from, Position to){
        if(getUnitAt(to) != null){
            return getUnitAt(to).getOwner().equals(getUnitAt(from).getOwner());
        }
        return false;
    }

    private boolean movementIsValid(Position from, Position to){
        UnitImpl unit = (UnitImpl) unitPositions.get(from);

        int columnDifference = to.getColumn() - from.getColumn();
        int rowDifference = to.getRow() - from.getRow();

        boolean isOwnedByPlayerInTurn = getPlayerInTurn().equals(unit.getOwner());
        boolean hasAvailableMoves = unit.getMoveCount() > 0;
        boolean maxOneTileAway = columnDifference >= -1
                && columnDifference <= 1
                && rowDifference >= -1
                && rowDifference <= 1;

        return maxOneTileAway
                && isTerrainValidForUnitPlacement(to, unit.getTypeString())
                && hasAvailableMoves
                && isOwnedByPlayerInTurn
                && !isFriendlyUnitInTheWay(from, to)
                && !unit.isFortified();
    }

    private void conquerIfEnemyCityAt(Position to) {
        CityImpl cityToBeConquered = (CityImpl) cityPositions.get(to);
        if(cityToBeConquered != null){
            cityToBeConquered.setOwner(getPlayerInTurn());
        }
    }

    private void incrementAge() {
        age += timeStrategy.incrementAge(age);
    }

    private void changeUnitPosition(Position from, Position to, UnitImpl unitToBeMoved) {
        unitPositions.remove(from);
        unitPositions.put(to, unitToBeMoved);
    }

    private void notifyWorldChangedAt(Position p){
        for(GameObserver o: observerList){
            o.worldChangedAt(p);
        }
    }

    private void notifyTurnEnds(){
        for(GameObserver o: observerList){
            o.turnEnds(getPlayerInTurn(), age);
        }
    }

    private void notifyTileFocusChangedAt(Position p){
        for(GameObserver o: observerList){
            o.tileFocusChangedAt(p);
        }
    }
}


