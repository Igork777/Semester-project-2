package handin_3.server.networking;

import handin_3.server.model.ChatManager;
import handin_3.server.model.ChatManagerImpl;
import handin_3.server.model.UserManager;
import handin_3.server.model.UserManagerImpl;
import handin_3.shared.ClientCallBack;
import handin_3.shared.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements Server {
    private ChatManager chatManager;
    private UserManager userManager;

    public ServerImpl() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            chatManager = new ChatManagerImpl();
            userManager = new UserManagerImpl();
            System.out.println("Server started");
        } catch (RemoteException e) {
            throw new RuntimeException("Constructor ServerImpl threw an RemoteException in ServerImpl");
        }
    }


    public void startServer() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("RMIServer", this);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String nickname, ClientCallBack client) {
        try {
            userManager.registerUser(nickname, client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(ClientCallBack client) {
        try {
            userManager.removeUser(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void broadcastMessage(String string, ClientCallBack client) {
        try {
            chatManager.broadcast(string, client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void broadcastMessage(String string, ClientCallBack client, String... nicknameClients) {
        try {
            chatManager.broadcast(string, client, nicknameClients);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestConnectedUsers(ClientCallBack client) {
        try {
            userManager.getAllNicknames(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
