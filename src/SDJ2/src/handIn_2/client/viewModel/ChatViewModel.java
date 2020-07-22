package handIn_2.client.viewModel;

import handIn_2.client.model.ChatModel;
import handIn_2.client.model.UserModel;
import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatViewModel implements PropertyChangeListener, ViewModel {
    private UserModel userModel;
    private ChatModel chatModel;
    private SimpleStringProperty userNameStringProperty, messageStringProperty, allMessagesStringProperty;
    private PropertyChangeSupport support;

    public ChatViewModel(UserModel userModel, ChatModel chatModel) {
        this.userModel = userModel;
        this.chatModel = chatModel;
        this.support = new PropertyChangeSupport(this);

        userNameStringProperty = new SimpleStringProperty();
        messageStringProperty = new SimpleStringProperty();
        allMessagesStringProperty = new SimpleStringProperty();
        allMessagesStringProperty.setValue("");

        userModel.addListener(this);
        chatModel.addListener(this);
        support.addPropertyChangeListener(userModel);
        support.addPropertyChangeListener(chatModel);
    }

    public void addListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
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

    public void sendMessage(String message) {
        Form form = new Form();
        form.add("Message", message);
        form.add("Nickname", userModel.getNickName());
        chatModel.sendMessage(form);
    }

    public void requestConnectedUsers(){
        chatModel.requestConnectedUsers();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Request request = (Request) evt.getNewValue();
        Request.Action action = request.getAction();
        Request.Topic topic = request.getTopic();
        Request.Type type = request.getType();
        Form form = request.getForm();
        if (evt.getPropertyName().equals(Event.REQ_FROM_CLIENT.name())) {
            switch (topic) {
                case USER: {
                    switch (action) {
                        case CREATE:
                        case UPDATE: {
                            userNameStringProperty.setValue(request.getForm().getValue("nickname"));
                            break;
                        }
                    }
                    break;
                }
                case MESSAGE: {
                    if (action == Request.Action.CREATE) {
                        support.firePropertyChange(Event.REQ_FROM_CLIENT.name(), null, request);
                    }
                    break;
                }
            }

        } else if (evt.getPropertyName().equals(Event.REQ_FROM_SERVER.name())) {
            switch (type){
                case RES:{
                    switch (topic){
                        case USER:{
                            switch (action){
                                case READ:{
                                    support.firePropertyChange(Event.BROAD_FROM_SERVER.name(), null, request);
                                    break;
                                }
                            }
                        break;
                        }
                    }
                    break;
                }
                case BROAD:{
                    switch (topic) {
                        case USER:{
                            switch (action){
                                case DELETE:{
                                    String str = "\n"+form.getValue("nickname") + " Left the Chat :(\n";
                                    appendToStringProperty(allMessagesStringProperty, str);
                                    support.firePropertyChange(Event.BROAD_FROM_SERVER.name(), null, request);
                                    break;
                                }case CREATE:{
                                    String str = "\n"+form.getValue("nickname") + " Joined the Chat :)\n";
                                    appendToStringProperty(allMessagesStringProperty, str);
                                    support.firePropertyChange(Event.BROAD_FROM_SERVER.name(), null, request);
                                    break;
                                }
                            }
                            break;
                        }
                        case MESSAGE:
                            switch (action) {
                                case CREATE:
                                    messageStringProperty.setValue("");
                                    appendToStringProperty(allMessagesStringProperty, form.getValue("nickname") + " : " + form.getValue("message").toString());
                                break;
                            }
                    }
                    break;
                }
            }
        }
    }

    private void appendToStringProperty(StringProperty stringProperty, String str){
        stringProperty.setValue(stringProperty.getValue() + "\n" + str);
    }
}
