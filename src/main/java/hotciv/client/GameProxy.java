package hotciv.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;
import hotciv.common.OperationNames;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;

public class GameProxy implements Game, ClientProxy {
    private Requestor requestor;
    public static final String OBJECT_ID = "singleton";
    private ArrayList<GameObserver> observerList;

    public GameProxy(Requestor requestor){
        this.requestor = requestor;
        observerList = new ArrayList<>();
    }

    @Override
    public Tile getTileAt(Position p) {
        Tile tile =
            requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.GET_TILE_AT, TileImpl.class, p);
        return tile;
    }

    @Override
    public Unit getUnitAt(Position p) {
        String id =
                requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.GET_UNIT_AT, String.class, p);
        if(id == null){
            return null;
        }
        Unit unit = new UnitProxy(id, requestor);
        return unit;
    }

    @Override
    public City getCityAt(Position p) {
        String id =
                requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.GET_CITY_AT, String.class, p);
        if(id == null){
            return null;
        }
        City city = new CityProxy(id, requestor);
        return city;
    }

    @Override
    public Player getPlayerInTurn() {
        Player playerInTurn =
                requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.GET_PLAYER_IN_TURN, Player.class);
        return playerInTurn;
    }

    @Override
    public Player getWinner() {
        Player winner =
                requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.GET_WINNER, Player.class);
        return winner;
    }

    @Override
    public int getAge() {
        int age =
                requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.GET_AGE, Integer.class);
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean validMove =
                requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.MOVE_UNIT, boolean.class, from, to);
        for (GameObserver o: observerList) {
            o.worldChangedAt(from);
            o.worldChangedAt(to);
        }
        return validMove;
    }

    @Override
    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.END_OF_TURN, null);
        for (GameObserver o: observerList) {
            o.turnEnds(getPlayerInTurn(), getAge());
        }
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.CHANGE_WORKFORCEFOCUS_IN_CITY_AT, null, p, balance);
        for (GameObserver o: observerList) {
            o.worldChangedAt(p);
        }
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.CHANGE_PRODUCTION_IN_CITY_AT, null, p, unitType);
        for (GameObserver o: observerList) {
            o.worldChangedAt(p);
        }
    }

    @Override
    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply(OBJECT_ID, OperationNames.PERFORM_UNIT_ACTION_AT, null, p);
        for (GameObserver o: observerList) {
            o.worldChangedAt(p);
        }
    }

    @Override
    public void addObserver(GameObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        for(GameObserver o: observerList){
            o.tileFocusChangedAt(position);
        }
    }
}
