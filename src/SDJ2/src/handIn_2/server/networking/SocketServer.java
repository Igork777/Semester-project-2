package handIn_2.server.networking;

import handIn_2.server.manager.UserManager;
import handIn_2.shared.Form;
import handIn_2.shared.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static handIn_2.server.RunServer.appLog;


public class SocketServer{
    private static ArrayList<UserManager> users;
    private static ArrayList<ClientHandler> clients;
    private PropertyChangeSupport support;
    private ServerSocket serverSocket;

    public SocketServer() throws IOException {
        users = new ArrayList<UserManager>();
        clients = new ArrayList<ClientHandler>();
        this.support = new PropertyChangeSupport(this);
        this.serverSocket = new ServerSocket(2910);

        while (true) {
            System.out.println("Waiting for the next client!");
            Socket socket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(socket);
            addClient(clientHandler);
            clientHandler.start();
        }
    }

    public static boolean isNickUnique(String nickname) {
        for (UserManager eachUser : users) {
            if (eachUser.getNickName().equals(nickname)) {
                return false;
            }
        }
        return true;
    }

    public static synchronized void broadcast(Request req) throws IOException {
        Request request = req.copy();
        request.setType(Request.Type.BROAD);

        for (ClientHandler client : clients) {
            client.write(request);
        }
    }

    public static synchronized void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        appLog.log("A New Client Connected!");
    }

    public static synchronized void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        appLog.log("A New Client Connected!");
    }

    public static synchronized void addUser(UserManager userManager) {
        users.add(userManager);
        appLog.log(userManager.getNickName() + " Joined the chat!");
    }

    public static synchronized void removeUser(UserManager userManager) {
        users.remove(userManager);
        appLog.log(userManager.getNickName() + " Left the chat!");
    }

    public static synchronized ArrayList<String> getConnectedUsers(){
        ArrayList<String> connectedUsers = new ArrayList<>();
        for (UserManager user: users) {
            connectedUsers.add(user.getNickName());
        }
        return  connectedUsers;
    }
}
