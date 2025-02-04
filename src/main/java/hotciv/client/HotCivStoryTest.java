package hotciv.client;

import com.google.gson.internal.bind.util.ISO8601Utils;
import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

public class HotCivStoryTest {


    public static void main (String[] args) throws IOException{
        new HotCivStoryTest(args[0], args[1]);
    }

    public HotCivStoryTest(String host, String port) {
        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(host, Integer.parseInt(port));
        Requestor request = new StandardJSONRequestor(crh);
        Game game = new GameProxy(request);
        City city = new CityProxy("cityStubId", request);
        Unit unit = new UnitProxy("unitStubId", request);
        createStory(game, city, unit);

    }

    public void createStory(Game game, City city, Unit unit) {
        System.out.println("=== Testing simple methods ===");
        System.out.println("-> Game age                : " + game.getAge());
        System.out.println("-> Game winner             : " + game.getWinner());
        System.out.println("-> Game PlayerInTurn       : " + game.getPlayerInTurn());
        System.out.println("-> Game move (2,2) - (3,3) : " + game.moveUnit(new Position(2,0), new Position(2,1)));
        game.endOfTurn();
        System.out.println("-> Player after turn end   : " + game.getPlayerInTurn());
        System.out.println("-> Tile at (0,0)           : " + game.getTileAt(new Position(0,0)).getTypeString());
        System.out.println("");
        System.out.println("-> City owner              : " + city.getOwner());
        System.out.println("-> City size               : " + city.getSize());
        System.out.println("-> City treasury           : " + city.getTreasury());
        System.out.println("-> City production         : " + city.getProduction());
        System.out.println("-> City workforce          : " + city.getWorkforceFocus());
        System.out.println("");
        System.out.println("-> Unit owner              : " + unit.getOwner());
        System.out.println("-> Unit moves              : " + unit.getMoveCount());
        System.out.println("-> Unit defensive strength : " + unit.getDefensiveStrength());
        System.out.println("-> Unit attacking strength : " + unit.getAttackingStrength());
    }
}