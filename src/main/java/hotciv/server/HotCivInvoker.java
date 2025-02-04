package hotciv.server;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.common.NameService;
import hotciv.common.NameServiceImpl;
import hotciv.common.OperationNames;
import hotciv.common.UnknownServantException;
import hotciv.framework.Game;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HotCivInvoker implements Invoker {
    private final Map<String, Invoker> invokerMap;

    private final Gson gson;
    private final NameService nameService;

    private Game servant;


    public HotCivInvoker(Game servant, NameServiceImpl nameService) {
        gson = new Gson();
        this.servant = servant;
        this.nameService = nameService;

        invokerMap = new HashMap<>();
        Invoker gameInvoker = new HotCivGameInvoker(servant, nameService);
        invokerMap.put(OperationNames.GAME_PREFIX, gameInvoker);
        Invoker cityInvoker = new HotCivCityInvoker(nameService);
        invokerMap.put(OperationNames.CITY_PREFIX, cityInvoker);
        Invoker unitInvoker = new HotCivUnitInvoker(nameService);
        invokerMap.put(OperationNames.UNIT_PREFIX, unitInvoker);
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        String operationName = requestObject.getOperationName();

        String reply;

        // Identify the invoker to use
        String type = operationName.substring(0, operationName.indexOf(OperationNames.SEPARATOR));
        Invoker subInvoker = invokerMap.get(type);

        // And do the upcall on the subInvoker
        try {
            reply = subInvoker.handleRequest(request);

        } catch (UnknownServantException e) {
            reply = gson.toJson(
                    new ReplyObject(
                            HttpServletResponse.SC_NOT_FOUND,
                            e.getMessage()));
        }

        return reply;
    }
}
