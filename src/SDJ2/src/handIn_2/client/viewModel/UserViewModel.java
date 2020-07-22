package handIn_2.client.viewModel;

import handIn_2.client.model.UserModel;
import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class UserViewModel implements PropertyChangeListener, ViewModel {
    private SimpleStringProperty nicknameStringProperty;
    private SimpleStringProperty errorStringProperty;
    private PropertyChangeSupport support;
    private UserModel userModel;

    public UserViewModel(UserModel userModel) {
        nicknameStringProperty = new SimpleStringProperty();
        errorStringProperty = new SimpleStringProperty();
        this.userModel = userModel;
        this.userModel.addListener(this);
        this.support = new PropertyChangeSupport(this);
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
    public void propertyChange(PropertyChangeEvent evt) {
        Event event = Event.valueOf(evt.getPropertyName());

        switch (event) {
            case REQ_FROM_SERVER: {
                Request request = (Request) evt.getNewValue();
                Request.Type type = request.getType();
                Request.Action action = request.getAction();
                Request.Topic topic = request.getTopic();
                Form form = request.getForm();

                if(type == Request.Type.RES &&  topic == Request.Topic.USER){
                    if(form.getValue("Verdict") != null || form.getValue("Nickname") != null){
                        boolean verdict = form.getValue("Verdict");
                        String nick = form.getValue("Nickname");
                        if(verdict){
                            nicknameStringProperty.setValue(nick);
                            errorStringProperty.setValue("");
                        }else{
                            Platform.runLater(() ->{
                                nicknameStringProperty.setValue(userModel.getNickName());
                                errorStringProperty.setValue(form.getValue("Reason").toString());
                            });
                        }
                        support.firePropertyChange(Event.REQ_FROM_SERVER.name(),null, request);
                    }else{
                        System.out.println("Something wrong");
                    }
                }
            }
        }

    }

    public void setNickName(String nickName) throws IOException {
        System.out.println(nickName);
        if (nickName == null || nickName.equals("") || nickName.replace(" ", "").length() < 3) {
            System.out.println("Nickname invalid!");
            errorStringProperty.setValue("Invalid nickname!");
            nicknameStringProperty.setValue(userModel.getNickName());
        } else {
            System.out.println("Nickname valid!");
            errorStringProperty.setValue("");
            if(userModel.isNewUser())
            {
                userModel.requestNewUser(nickName);
            }else{
                userModel.requestSetNickname(nickName);
            }
        }
    }

    public void addListener(PropertyChangeListener listener){
        this.support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener){
        this.support.removePropertyChangeListener(listener);
    }
}
