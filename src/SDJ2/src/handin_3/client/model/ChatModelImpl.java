package handin_3.client.model;

import handin_3.client.networking.Client;
import handin_3.shared.Event;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ChatModelImpl implements ChatModel {
    private Client client;
    private PropertyChangeSupport support;

    public ChatModelImpl(Client client) {
        this.support = new PropertyChangeSupport(this);
        this.client = client;
        try {
            this.client.addListener(Event.MessagePushNotification.name(), this::sendToChatViewModel);
            this.client.addListener(Event.UserLeftPushNotification.name(), this::sendToChatViewModel);
            this.client.addListener(Event.UserJoinedPushNotification.name(), this::sendToChatViewModel);
            this.client.addListener(Event.AllNicknamesAreReceived.name(), this::sendToChatViewModel);
            this.client.addListener(Event.RegistrationIsSuccessful.name(), this::sendToChatViewModel);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void sendToChatViewModel(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent);
    }


    @Override
    public void sendMessage(String message) {

        try {
            client.sendMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException("Method broadcastMessage threw an exception in ChatModelImpl");
        }
    }

    @Override
    public void sendMessage(String message, String... nicknames) {
        try {
            client.sendMessage(message, nicknames);
        } catch (RemoteException e) {
            throw new RuntimeException("Method broadcastMessage threw an exception in ChatModelImpl");
        }
    }

    @Override
    public void requestConnectedUsers() {
        try {
            client.requestConnectedUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
}
