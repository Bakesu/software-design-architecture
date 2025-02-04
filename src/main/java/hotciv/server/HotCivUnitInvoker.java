package hotciv.server;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.common.NameService;
import hotciv.common.NameServiceImpl;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.common.OperationNames;

import javax.servlet.http.HttpServletResponse;

public class HotCivUnitInvoker implements Invoker {
    private final Gson gson;
    private NameService nameService;

    public HotCivUnitInvoker(NameService nameService) {
        gson = new Gson();
        this.nameService = nameService;
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);

        String operationName = requestObject.getOperationName();

        ReplyObject reply = null;

        Unit unit = lookupUnit(requestObject.getObjectId());

        if (operationName.equals(OperationNames.GET_UNIT_OWNER)) {
            Player owner = unit.getOwner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(owner));


        } else if (operationName.equals(OperationNames.GET_TYPE_STRING)) {
            String type = unit.getTypeString();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(type));


        } else if (operationName.equals(OperationNames.GET_MOVE_COUNT)) {
            int moveCount = unit.getMoveCount();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(moveCount));


        } else if (operationName.equals(OperationNames.GET_DEFENSIVE_STRENGTH)) {
            int dStrength = unit.getDefensiveStrength();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(dStrength));


        } else if (operationName.equals(OperationNames.GET_ATTACKING_STRENGTH)) {
            int aStrength = unit.getAttackingStrength();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(aStrength));


        }
        // And marshall the reply
        return gson.toJson(reply);
    }

    private Unit lookupUnit(String objectId) {
        return nameService.getUnit(objectId);
    }
}
