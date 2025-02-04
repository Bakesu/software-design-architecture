package hotciv.server;

import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.common.NameServiceImpl;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.variants.factories.AlphaCivFactory;
import hotciv.standard.variants.factories.DeltaCivFactory;
import hotciv.standard.variants.factories.SemiCivFactory;
import hotciv.stub.StubGame2;
import hotciv.view.CivDrawing;
import hotciv.view.tool.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

import java.net.InetAddress;

public class HotCivServer {
    private NameServiceImpl nameService;

    public static void main(String[] args) throws Exception {
        // Command line argument parsing and validation
        new HotCivServer(args[0]); // No error handling!
    }

    public HotCivServer(String port) throws Exception {
        nameService = new NameServiceImpl();
        // Define the server side delegates
        Game gameServant = new GameImpl(new SemiCivFactory());
        Invoker invoker = new HotCivInvoker(gameServant, nameService);
        // Configure a socket based server request handler
        ServerRequestHandler ssrh =
                new SocketServerRequestHandler();
        ssrh.setPortAndInvoker(Integer.parseInt(port), invoker);

        // Welcome
        System.out.println("=== HotCiv Socket based Server Request Handler (port:"
                + port + " on address " + InetAddress.getLocalHost().getHostAddress() + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
        ssrh.start();
    }

}
