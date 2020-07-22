package handin_3.client.networking;

import handin_3.shared.ClientCallBack;
import handin_3.shared.Event;
import handin_3.shared.Server;
import handin_3.shared.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements ClientCallBack, Client {
    private Server server;
    private PropertyChangeSupport support;

    public RMIClient() {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (Server) registry.lookup("RMIServer");
            support = new PropertyChangeSupport(this);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(String nickName) {
        try {
            server.addUser(nickName, this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendMessage(String message) {
        try {
            server.broadcastMessage(message, this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message, String... nicknameClients) {
        try {
            server.broadcastMessage(message, this, nicknameClients);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMessage(String message) {
        support.firePropertyChange(Event.MessagePushNotification.name(), null, message);
    }

    @Override
    public void getAllNicknamesCallback(ArrayList<String> list) {
        support.firePropertyChange(Event.AllNicknamesAreReceived.name(), null, list);
    }

    @Override
    public void registerUserCallbackSuccess(String nickname) {
        support.firePropertyChange(Event.RegistrationIsSuccessful.name(), null, nickname);
    }

    @Override
    public void registerUserCallbackFailure(String nickname) {
        support.firePropertyChange(Event.RegistrationIsNotSuccessful.name(), null, nickname);
    }

    @Override
    public void userJoinedPushNotification(String nickname) {
        support.firePropertyChange(Event.UserJoinedPushNotification.name(), null, nickname);
    }

    @Override
    public void userLeftPushNotification(String nickname) {
        support.firePropertyChange(Event.UserLeftPushNotification.name(), null, nickname);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }
    @Override
    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void removeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void requestConnectedUsers() {
        try {
            server.requestConnectedUsers(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser() {
        try {
            server.removeUser(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
