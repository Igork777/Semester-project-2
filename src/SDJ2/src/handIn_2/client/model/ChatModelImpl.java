package handIn_2.client.model;

import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;
import handIn_2.shared.Request.Type;
import handIn_2.shared.Request.Action;
import handIn_2.shared.Request.Topic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelImpl implements ChatModel {
    private PropertyChangeSupport support;

    public ChatModelImpl() {
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Request request = (Request) evt.getNewValue();
        Type type = request.getType();
        Action action = request.getAction();
        Topic topic = request.getTopic();
        Event event = Event.valueOf(evt.getPropertyName());


        if (event == Event.REQ_FROM_SERVER) {
            switch (type) {
                case RES:{
                    switch (topic){
                        case USER:{
                            switch (action){
                                case READ:{
                                    support.firePropertyChange(Event.REQ_FROM_SERVER.name(), null, request);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                case BROAD: {
                    switch (action) {
                        case CREATE:
                            switch (topic) {
                                case USER:
                                    support.firePropertyChange(Event.REQ_FROM_SERVER.name(), null, request);
                                    System.out.println("User joined");
                                    break;
                                case MESSAGE:
                                    support.firePropertyChange(Event.REQ_FROM_SERVER.name(), null, request);
                                    break;
                            }
                            break;
                        case DELETE: {
                            switch (topic) {
                                case USER: {
                                    support.firePropertyChange(Event.REQ_FROM_SERVER.name(), null, request);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void sendMessage(Form form) {
        Request req = new Request(Type.ASK, Action.CREATE, Topic.MESSAGE, form);
        support.firePropertyChange(Event.REQ_FROM_CLIENT.name(), null, req);
    }

    @Override
    public void requestConnectedUsers() {
        Request request = new Request(Type.ASK, Action.READ, Topic.USER);
        support.firePropertyChange(Event.REQ_FROM_CLIENT.name(), null, request);
    }
}
