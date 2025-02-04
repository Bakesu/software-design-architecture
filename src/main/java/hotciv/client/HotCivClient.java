package hotciv.client;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Unit;
import hotciv.view.CivDrawing;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import java.io.IOException;

public class HotCivClient {


    public static void main (String[] args) throws IOException {
        new HotCivClient(args[0], args[1]);
    }

    public HotCivClient(String host, String port) {
        //System.out.println("HotCivStoryTest: asked to do operation " + operation+"for player" + player);
        ClientRequestHandler crh = new SocketClientRequestHandler();
        crh.setServer(host, Integer.parseInt(port));
        Requestor request = new StandardJSONRequestor(crh);
        Game game = new GameProxy(request);
        DrawingEditor editor =
                new MiniDrawApplication("HotCivClient", new HotCivFactory4(game) );

        editor.open();
        editor.setTool( new CompositionTool(editor, game) );
    }
}
