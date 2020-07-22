package handin_3.server;

import handin_3.server.networking.ServerImpl;

import java.io.IOException;
import java.rmi.AlreadyBoundException;

public class RunServer {


    public static void main(String[] args) throws IOException, AlreadyBoundException {
        ServerImpl server = new ServerImpl();
        server.startServer();
    }

}
