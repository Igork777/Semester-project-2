package handin_3.client.viewModel;

import handin_3.client.model.UserModel;
import handin_3.shared.Event;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserViewModel implements ViewModel {
    private SimpleStringProperty nicknameStringProperty;
    private SimpleStringProperty errorStringProperty;
    private PropertyChangeSupport support;
    private UserModel userModel;

    public UserViewModel(UserModel userModel) {
        nicknameStringProperty = new SimpleStringProperty();
        errorStringProperty = new SimpleStringProperty();
        this.userModel = userModel;
        this.userModel.addListener(Event.RegistrationIsNotSuccessful.name(), this::onRegistrationIsNotSuccessful);
        this.userModel.addListener(Event.RegistrationIsSuccessful.name(), this::onRegistrationSuccessful);
        this.support = new PropertyChangeSupport(this);
    }

    public void register(String nickName) {
        if (nickName == null || nickName.equals("") || nickName.replace(" ", "").length() < 3 || nickName.length() > 20) {
            errorStringProperty.setValue("Invalid nickname");
        } else {
            userModel.createNewUser(nickName);
        }
    }

    private void onRegistrationSuccessful(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange(propertyChangeEvent);
    }

    private void onRegistrationIsNotSuccessful(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() -> {
            errorStringProperty.set("Nickname " + propertyChangeEvent.getNewValue().toString() + " is already used");
            nicknameStringProperty.set("");
        });
    }


    public SimpleStringProperty getNickStringProperty() {
        return nicknameStringProperty;
    }

    public SimpleStringProperty getErrorStringProperty() {
        return errorStringProperty;
    }

    public UserModel getUserModel() {
        return userModel;
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
