package hotciv.standard.variants;

import hotciv.framework.*;
import hotciv.framework.variants.factories.GameFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.variants.factories.AlphaCivFactory;

import java.util.ArrayList;

public class GameLogDecorator implements Game {
    private Game game;
    private Game decoratee;

    public GameLogDecorator(GameFactory gameFactory) {
        this.game = new GameImpl(gameFactory);
    }

    private GameLogDecorator(Game game){
        this.game = game;
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return  game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        boolean moveIsValid = game.moveUnit(from, to);
        if(moveIsValid) {
            System.out.println(getPlayerInTurn().toString() + " moves " + getUnitAt(to).getTypeString() + " from " + from.toString() + " to " + to.toString() + ".");
        }
        return moveIsValid;
    }

    @Override
    public void endOfTurn() {
        System.out.println(getPlayerInTurn().toString() + " ends turn.");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        String logBalance = focusToLogConverter(balance);

        System.out.println(getPlayerInTurn() + " changes work force focus in city at " + p + " to " + logBalance + " focus.");
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println(getPlayerInTurn() + " changes production in city at " + p + " to " + unitType + ".");
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        game.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {
        game.addObserver(observer);
    }

    @Override
    public void setTileFocus(Position position) {
        game.setTileFocus(position);
    }

    private String focusToLogConverter(String balance) {
        switch (balance){
            case GameConstants.foodFocus:
                return "food";
            case GameConstants.productionFocus:
                return "production";
            default:
                return balance;
        }
    }

    /**
    public void switchLogging(){
        if (game == decoratee) {
            decoratee = game;
            game = new GameLogDecorator(game);
        } else {
            game = decoratee;
        }
    } */
}
