package hotciv.standard;

import frds.broker.ClientRequestHandler;
import hotciv.common.NameService;
import hotciv.common.NameServiceImpl;
import hotciv.server.HotCivInvoker;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.client.LocalMethodClientRequestHandler;
import hotciv.client.UnitProxy;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.stub.StubGame2;
import hotciv.stub.StubUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TestUnitProxy {
    private Unit unit;
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

        nameService.putUnit("singleton", new StubUnit(GameConstants.LEGION, Player.GREEN));

        unit = new UnitProxy("singleton", requestor);
    }

    @Test
    void shouldHaveOwner(){
        Player owner = unit.getOwner();
        assertThat(owner, is(Player.GREEN));
    }

    @Test
    void shouldHaveTypeString(){
        String type = unit.getTypeString();
        assertThat(type, is(GameConstants.LEGION));
    }

    @Test
    void shouldHaveMoveCount(){
        int moveCount = unit.getMoveCount();
        assertThat(moveCount, is(1));
    }

    @Test
    void shouldHaveDefensiveStrength(){
        int dStrength = unit.getDefensiveStrength();
        assertThat(dStrength, is(0));
    }

    @Test
    void shouldHaveAttackingStrength(){
        int aStrength = unit.getAttackingStrength();
        assertThat(aStrength, is(0));
    }
}