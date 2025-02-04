package hotciv.server;

import frds.broker.Invoker;
import frds.broker.ServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.common.NameServiceImpl;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.stub.StubGame2;

import java.net.InetAddress;

public class HotCivStoryServer {
    private NameServiceImpl nameService;

    public static void main(String[] args) throws Exception {
        // Command line argument parsing and validation
        new HotCivStoryServer(args[0]); // No error handling!
    }

    public HotCivStoryServer(String port) throws Exception {
        nameService = new NameServiceImpl();
        // Define the server side delegates
        Game gameServant = new StubGame2();
        nameService.putCity("cityStubId", gameServant.getCityAt(new Position(1,1)));
        nameService.putUnit("unitStubId", gameServant.getUnitAt(new Position(2,0)));
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
