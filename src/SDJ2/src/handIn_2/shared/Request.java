package handIn_2.shared;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Request implements Serializable {

    public enum Type {
        ASK,
        RES,
        BROAD;
    }

    public enum Action {
        VALIDATE,
        CREATE,
        READ,
        UPDATE,
        DELETE;
    }

    public enum Topic {
        MESSAGE,
        NICKNAME,
        USER,
        NR_OF_CHATTERS,
    }

    private Type type;
    private Topic topic;
    private Action action;
    private Form form;

    public Request() {
    }

    public Request(Type type, Topic topic) {
        this.type = type;
        this.topic = topic;
    }

    public Request(Type type, Action action, Topic topic) {
        this.type = type;
        this.topic = topic;
        this.action = action;
    }

    public Request(Type type, Action action, Topic topic, Form form) {
        this.type = type;
        this.topic = topic;
        this.action = action;
        this.form = form;
    }

    public Request(Type type, Action action, Topic topic, FormField... fields) {
        this.type = type;
        this.topic = topic;
        this.action = action;
        this.form = new Form(fields);
    }

    public Form getForm() {
        return form;
    }

    public Type getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setForm(FormField... fields) {
        this.form = new Form(fields);
    }

    private boolean isValid() {
        return type != null && action != null && topic != null;
    }

    public void send(ObjectOutputStream stream) throws IOException {
        if (isValid()) {
            stream.writeObject(this);
        } else {
            throw new RuntimeException("Invalid request");
        }
    }

    public Request copy(){
        Form newForm = new Form(form.getFields());
        return new Request(type, action, topic, newForm);
    }
}

