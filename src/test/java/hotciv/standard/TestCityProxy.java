package hotciv.standard;

import frds.broker.ClientRequestHandler;
import hotciv.common.NameService;
import hotciv.common.NameServiceImpl;
import hotciv.server.HotCivInvoker;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.client.CityProxy;
import hotciv.client.LocalMethodClientRequestHandler;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.stub.StubCity;
import hotciv.stub.StubGame2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCityProxy {

    private City city;
    private StubGame2 servant;
    private NameServiceImpl nameService;

    @BeforeEach
    void setUp() {
        servant = new StubGame2();

        nameService = new NameServiceImpl();

        Invoker invoker = new HotCivInvoker(servant, nameService);

        ClientRequestHandler crh =
                new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);

        nameService.putCity("singleton", new StubCity(Player.GREEN, GameConstants.ARCHER, GameConstants.productionFocus));

        city = new CityProxy("singleton", requestor);
    }

    @Test
    void shouldHaveOwner(){
        Player owner = city.getOwner();
        assertThat(owner, is(Player.GREEN));
    }

    @Test
    void shouldHaveSize(){
        int size = city.getSize();
        assertThat(size, is(1));
    }

    @Test
    void shouldHaveTreasury(){
        int treasury = city.getTreasury();
        assertThat(treasury, is(6));
    }

    @Test
    void shouldHaveProduction(){
        String production = city.getProduction();
        assertThat(production, is(GameConstants.ARCHER));
    }

    @Test
    void shouldHaveWorkForce(){
        String workForceFocus = city.getWorkforceFocus();
        assertThat(workForceFocus, is(GameConstants.productionFocus));

    }


}