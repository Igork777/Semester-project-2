package handIn_2.server;

import handIn_2.server.networking.SocketServer;
import handIn_2.shared.Log;

import java.io.IOException;

public class RunServer {
    public static Log appLog = new Log();
    private static SocketServer socketServer;

    public static void main(String[] args) throws IOException {
        socketServer = new SocketServer();
    }

}
