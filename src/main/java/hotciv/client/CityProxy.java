package hotciv.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.common.OperationNames;

public class CityProxy implements City, ClientProxy {
    private Requestor requestor;
    private String objectId;

    public CityProxy(String objectId, Requestor requestor) {
        this.requestor = requestor;
        this.objectId = objectId;
    }

    @Override
    public Player getOwner() {
        Player owner =
            requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_CITY_OWNER, Player.class);
        return owner;
    }


    @Override
    public int getSize() {
        int size =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_SIZE, Integer.class);
        return size;
    }

    @Override
    public int getTreasury() {
        int treasury =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_TREASURY, Integer.class);
        return treasury;
    }

    @Override
    public String getProduction() {
        String production =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_PRODUCTION, String.class);
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        String workForceFocus =
                requestor.sendRequestAndAwaitReply(objectId, OperationNames.GET_WORK_FORCE_FOCUS, String.class);
        return workForceFocus;
    }

    @Override
    public String getId() {
        return objectId;
    }
}
