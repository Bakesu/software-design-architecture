package hotciv.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.common.OperationNames;

public class UnitProxy implements Unit, ClientProxy {

    private String objectId;
    private final Requestor requestor;

    public UnitProxy(String objectId, Requestor requestor) {
        this.requestor = requestor;
        this.objectId = objectId;
    }

    @Override
    public String getTypeString() {
        String type =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_TYPE_STRING, String.class);
        return type;
    }

    @Override
    public Player getOwner() {
        Player owner =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_UNIT_OWNER, Player.class);
        return owner;
    }

    @Override
    public int getMoveCount() {
        int moveCount =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_MOVE_COUNT, Integer.class);
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        int dStrength =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_DEFENSIVE_STRENGTH, Integer.class);
        return dStrength;
    }

    @Override
    public int getAttackingStrength() {
        int aStrength =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_ATTACKING_STRENGTH, Integer.class);
        return aStrength;
    }

    @Override
    public String getId() {
        return objectId;
    }
}
