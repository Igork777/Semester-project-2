package handin_3.client.viewModel;

import handin_3.client.model.ChatModel;
import handin_3.client.model.UserModel;
import handin_3.shared.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatViewModel implements ViewModel {
    private ChatModel chatModel;
    private SimpleStringProperty userNameStringProperty, messageStringProperty, allMessagesStringProperty;
    private PropertyChangeSupport support;

    public ChatViewModel(ChatModel chatModel) {
        this.chatModel = chatModel;
        this.support = new PropertyChangeSupport(this);

        userNameStringProperty = new SimpleStringProperty();
        messageStringProperty = new SimpleStringProperty();
        allMessagesStringProperty = new SimpleStringProperty();
        allMessagesStringProperty.setValue("");

        chatModel.addListener(Event.RegistrationIsSuccessful.name(), this::onRegistrationIsSuccessful);
        chatModel.addListener(Event.UserJoinedPushNotification.name(), this::onUserJoinedPushNotification);
        chatModel.addListener(Event.UserLeftPushNotification.name(), this::onUserLeftPushNotification);
        chatModel.addListener(Event.AllNicknamesAreReceived.name(), this::onAllNicknamesAreReceived);
        chatModel.addListener(Event.MessagePushNotification.name(), this::onMessagePushNotification);
    }

    public void sendMessage(String message) {
        chatModel.sendMessage(message);
    }

    private void onUserLeftPushNotification(PropertyChangeEvent propertyChangeEvent) {
        appendToStringProperty(allMessagesStringProperty, propertyChangeEvent.getNewValue().toString() + " left the chat =(");
    }

    private void onUserJoinedPushNotification(PropertyChangeEvent propertyChangeEvent) {
        appendToStringProperty(allMessagesStringProperty, propertyChangeEvent.getNewValue().toString() + " joined the chat =)");
    }

    private void onMessagePushNotification(PropertyChangeEvent propertyChangeEvent) {

        appendToStringProperty(allMessagesStringProperty, propertyChangeEvent.getNewValue().toString());
    }


    private void onAllNicknamesAreReceived(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent);
    }

    private void onRegistrationIsSuccessful(PropertyChangeEvent propertyChangeEvent) {
        userNameStringProperty.set(propertyChangeEvent.getNewValue().toString());
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

    public SimpleStringProperty getUserNameStringProperty() {
        return userNameStringProperty;
    }

    public SimpleStringProperty getMessageStringProperty() {
        return messageStringProperty;
    }


    public SimpleStringProperty getAllMessagesTextAreaStringAreaProperty() {
        return allMessagesStringProperty;
    }

    private void appendToStringProperty(StringProperty stringProperty, String str) {
        stringProperty.setValue(stringProperty.getValue() + "\n" + str);
    }

    public void requestConnectedUsers() {
        chatModel.requestConnectedUsers();
    }
}
