package handIn_2.client.model;


import handIn_2.client.core.ModelFactory;
import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class UserModelImpl implements UserModel {

    private boolean newUser;
    private String nickName;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public UserModelImpl() {
        this.newUser = true;
    }

    @Override
    public void requestNewUser(String nickName) {
        Request request = new Request(Request.Type.ASK, Request.Action.CREATE, Request.Topic.USER);
        Form requestForm = new Form();
        requestForm.add("Nickname", nickName);
        request.setForm(requestForm);
        support.firePropertyChange(Event.REQ_FROM_CLIENT.name(), null, request);
        System.out.println("Requesting new user creation...");
    }

    @Override
    public void requestSetNickname(String nickname) {
        Request request = new Request(Request.Type.ASK, Request.Topic.USER);
        Form requestForm = new Form();
        requestForm.add("Nickname", nickName);
        request.setForm(requestForm);
        request.setAction(Request.Action.UPDATE);

        support.firePropertyChange(Event.REQ_FROM_CLIENT.name(), null, request);
    }

    @Override
    public void setNickName(String nickName) throws IOException {
            this.nickName = nickName;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean isNewUser() {
        return this.newUser;
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
        Event event = Event.valueOf(evt.getPropertyName());

        switch (event) {
            case REQ_FROM_SERVER: {
                Request request = (Request) evt.getNewValue();
                Request.Type type = request.getType();
                Request.Action action = request.getAction();
                Request.Topic topic = request.getTopic();
                Form form = request.getForm();

                switch (type){
                    case RES:{
                        switch (action){
                            case CREATE:{
                                switch (topic){
                                    case USER:{
                                        if(form.getValue("Verdict") != null || form.getValue("Nickname") != null){
                                            boolean verdict = form.getValue("Verdict");
                                            String nick = form.getValue("Nickname");
                                            if(verdict){
                                                System.out.println("Nickname approved by server");
                                                nickName = nick;
                                                newUser = false;
                                            }else{
                                                System.out.println("Nickname taken");
                                            }
                                            support.firePropertyChange(Event.REQ_FROM_SERVER.name(), null, request);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return "User";
    }
}
