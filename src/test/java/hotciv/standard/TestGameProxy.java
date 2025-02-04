package hotciv.standard;

import frds.broker.*;
import hotciv.client.GameProxy;
import hotciv.client.LocalMethodClientRequestHandler;
import hotciv.common.NameServiceImpl;
import hotciv.framework.*;

import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.server.HotCivInvoker;
import hotciv.standard.stubs.StubCity;
import hotciv.stub.NullObserver;
import hotciv.stub.StubUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestGameProxy {
    private Game game;
    private StubGame3 servant;

    @BeforeEach
    void setUp() {
        servant = new StubGame3();
        GameObserver nullObserver = new NullObserver();
        servant.addObserver(nullObserver);

        Invoker invoker = new HotCivInvoker(servant, new NameServiceImpl());

        ClientRequestHandler crh =
                new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);

        game = new GameProxy(requestor);
        game.addObserver(nullObserver);
    }

    @Test
    void shouldHaveWinner() {
        Player winner = game.getWinner();
        assertThat(winner, is(Player.YELLOW));
    }

    @Test
    void shouldHaveAge() {
        int age = game.getAge();
        assertThat(age, is(42));
    }

    @Test
    void shouldHavePlayerInTurn() {
        Player playerInTurn = game.getPlayerInTurn();
        assertThat(playerInTurn, is(Player.GREEN));
    }

    @Test
    void shouldHaveMoveUnit() {
     assertTrue(game.moveUnit(new Position(1,1), new Position(0,0)));
    }

    @Test
    void shouldHaveEndedTurn(){
        game.endOfTurn();
        assertThat(servant.turnEnded, is(1));
    }

    @Test
    void shouldHaveChangedWorkforceFocus(){
        game.changeWorkForceFocusInCityAt(new Position(1,1), GameConstants.foodFocus);
        assertThat(servant.workForceFocusChanged, is(1));
    }

    @Test
    void shouldHaveChangedProduction(){
        game.changeProductionInCityAt(new Position(1,1), GameConstants.CARAVAN);
        assertThat(servant.productionChanged, is(1));
    }

    @Test
    void shouldHavePerformedUnitAction(){
        game.performUnitActionAt(new Position(1,1));
        assertThat(servant.performedUnitAction, is(1));
    }

    @Test
    void shouldHaveTile(){
        String tile = game.getTileAt(new Position(0,0)).getTypeString();
        assertThat(tile, is(GameConstants.MOUNTAINS));
    }

    @Test
    void shouldHaveCityAt(){
        City city = game.getCityAt(new Position(1,1));
        assertThat(city.getOwner(), is(Player.GREEN));
        assertThat(city.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    void shouldHaveUnitAt(){
        Unit unit = game.getUnitAt(new Position(1,1));
        assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
        assertThat(unit.getOwner(), is(Player.YELLOW));
    }

    public class StubGame3 implements Game, Servant {
        private int turnEnded = 0;
        private int workForceFocusChanged = 0;
        private int productionChanged = 0;
        private int performedUnitAction = 0;

        @Override
        public Tile getTileAt(Position p) {
            return new TileImpl(GameConstants.MOUNTAINS);
        }


        Position green_city_position = new Position(1,1);

        @Override
        public Unit getUnitAt(Position p) {
            if(p.equals(green_city_position)){
                return new StubUnit(GameConstants.SETTLER, Player.YELLOW);
            }
            return null;
        }

        @Override
        public City getCityAt(Position p) {
            if (p.equals(green_city_position)) {
                return new StubCity(Player.GREEN, GameConstants.ARCHER, GameConstants.foodFocus);
            }
            return null;
        }

        @Override
        public Player getPlayerInTurn() {
            return Player.GREEN;
        }

        @Override
        public Player getWinner() {
            return Player.YELLOW;
        }

        @Override
        public int getAge() {
            return 42;
        }

        @Override
        public boolean moveUnit(Position from, Position to) {
            return true;
        }

        @Override
        public void endOfTurn() {
            turnEnded++;
        }

        @Override
        public void changeWorkForceFocusInCityAt(Position p, String balance) {
            workForceFocusChanged++;
        }

        @Override
        public void changeProductionInCityAt(Position p, String unitType) {
            productionChanged++;
        }

        @Override
        public void performUnitActionAt(Position p) {
            performedUnitAction++;
        }

        @Override
        public void addObserver(GameObserver observer) {

        }

        @Override
        public void setTileFocus(Position position) {

        }
    }
}