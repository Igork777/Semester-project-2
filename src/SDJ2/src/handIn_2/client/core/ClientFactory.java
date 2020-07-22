package handIn_2.client.core;

import handIn_2.client.networking.Client;
import handIn_2.client.networking.SocketClient;

import java.io.IOException;

public class ClientFactory  {
    private Client client;
    private Thread clientThread;

    public ClientFactory() throws IOException {
        this.client = new SocketClient();
        this.clientThread = new Thread(client);
        this.clientThread.setDaemon(true);
        this.clientThread.start();
        System.out.println("");
    }

    public Client getClient() {
        return client;
    }
}
