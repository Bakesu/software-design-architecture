package hotciv.server;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.common.NameService;
import hotciv.common.NameServiceImpl;
import hotciv.framework.*;
import hotciv.common.OperationNames;

import javax.servlet.http.HttpServletResponse;

public class HotCivCityInvoker implements Invoker {
    private final Gson gson;
    private NameService nameService;

    public HotCivCityInvoker(NameService nameService) {
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

        City city = lookupCity(requestObject.getObjectId());

        if (operationName.equals(OperationNames.GET_CITY_OWNER)) {
            Player owner = city.getOwner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(owner));
        } else if (operationName.equals(OperationNames.GET_SIZE)) {
            int size = city.getSize();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(size));

        } else if (operationName.equals(OperationNames.GET_TREASURY)) {
            int treasury = city.getTreasury();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(treasury));

        } else if (operationName.equals(OperationNames.GET_PRODUCTION)) {
            String production = city.getProduction();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(production));

        } else if (operationName.equals(OperationNames.GET_WORK_FORCE_FOCUS)) {
            String workForceFocus = city.getWorkforceFocus();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(workForceFocus));
        }
        // And marshall the reply
        return gson.toJson(reply);
    }

    private City lookupCity(String objectId) {
        return nameService.getCity(objectId);
    }
}
