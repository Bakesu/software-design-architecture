package hotciv.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.common.NameService;
import hotciv.common.NameServiceImpl;
import hotciv.framework.*;
import hotciv.common.OperationNames;
import hotciv.standard.CityImpl;

import javax.servlet.http.HttpServletResponse;

public class HotCivGameInvoker implements Invoker {

    private final Game game;
    private final Gson gson;
    private final NameService nameService;

    public HotCivGameInvoker(Game servant, NameService nameService) {
        game = servant;
        gson = new Gson();
        this.nameService = nameService;
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);

        String operationName = requestObject.getOperationName();
        String payload = requestObject.getPayload();

        JsonArray array =
                new JsonParser().parse(payload).getAsJsonArray();

        ReplyObject reply = null;

    /* As there is only one TeleMed instance (a singleton)
       the objectId is not used for anything in our case.
     */
            // Dispatching on all known operations
            // Each dispatch follows the same algorithm
            // a) retrieve parameters from json array (if any)
            // b) invoke servant method
            // c) populate a reply object with return values

            if (operationName.equals(OperationNames.GET_WINNER)) {
                Player winner = game.getWinner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                        gson.toJson(winner));


            } else if (operationName.equals(OperationNames.GET_AGE)) {
                int age = game.getAge();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                        gson.toJson(age));


            } else if (operationName.equals(OperationNames.GET_PLAYER_IN_TURN)) {
                Player playerInTurn = game.getPlayerInTurn();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                        gson.toJson(playerInTurn));


            } else if (operationName.equals(OperationNames.MOVE_UNIT)) {
                Position from = gson.fromJson(array.get(0), Position.class);
                Position to = gson.fromJson(array.get(1), Position.class);
                boolean moveUnit = game.moveUnit(from, to);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(moveUnit));


            } else if (operationName.equals(OperationNames.END_OF_TURN)) {
                game.endOfTurn();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, "Turn ended");


            } else if (operationName.equals(OperationNames.CHANGE_WORKFORCEFOCUS_IN_CITY_AT)) {
                Position position = gson.fromJson(array.get(0), Position.class);
                String balance = gson.fromJson(array.get(1), String.class);
                game.changeWorkForceFocusInCityAt(position, balance);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, "Workforce focus changed to " + balance + " in city at " + position);


            } else if (operationName.equals(OperationNames.CHANGE_PRODUCTION_IN_CITY_AT)) {
                Position position = gson.fromJson(array.get(0), Position.class);
                String production = gson.fromJson(array.get(1), String.class);
                game.changeProductionInCityAt(position, production);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, "Production changed to " + production + " in city at " + position);


            } else if (operationName.equals(OperationNames.PERFORM_UNIT_ACTION_AT)) {
                Position position = gson.fromJson(array.get(0), Position.class);
                game.performUnitActionAt(position);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, "Unit Action called at " + position);


            } else if (operationName.equals(OperationNames.GET_TILE_AT)) {
                Position position = gson.fromJson(array.get(0), Position.class);
                Tile tile = game.getTileAt(position);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(tile));


            } else if (operationName.equals(OperationNames.GET_CITY_AT)) {
                Position position = gson.fromJson(array.get(0), Position.class);
                City city = game.getCityAt(position);
                String id = null;
                if(city != null){
                    id = city.getId();
                    nameService.putCity(id, city);
                }
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));


            } else if (operationName.equals(OperationNames.GET_UNIT_AT)) {
                Position position = gson.fromJson(array.get(0), Position.class);
                Unit unit = game.getUnitAt(position);
                String id = null;
                if(unit != null) {
                    id = unit.getId();
                    nameService.putUnit(id, unit);
                }
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
            }
        // And marshall the reply
        return gson.toJson(reply);
    }
}

