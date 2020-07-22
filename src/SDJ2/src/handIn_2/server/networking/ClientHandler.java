package handIn_2.server.networking;

import handIn_2.server.manager.UserManager;
import handIn_2.server.manager.UserManagerImpl;
import handIn_2.shared.*;
import handIn_2.shared.Request;
import handIn_2.shared.Request.Type;
import handIn_2.shared.Request.Topic;
import handIn_2.shared.Request.Action;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread implements Subject {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private PropertyChangeSupport support;
    private UserManager user;

    public ClientHandler(Socket clientSocket) {
        this.socket = clientSocket;
        this.support = new PropertyChangeSupport(this);
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                handleRequest(read());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Request read() throws IOException, ClassNotFoundException {
        try {
            Request request = (Request) inputStream.readObject();
            return request;
        } catch (java.net.SocketException e) {
            Form form = new Form();
            form.add("nickname", user.getNickName());
            SocketServer.removeClient(this);
            SocketServer.removeUser(user);
            form.add("Connected Users", SocketServer.getConnectedUsers());
            SocketServer.broadcast(new Request(Type.BROAD, Action.DELETE, Topic.USER, form));
        }
        return null;
    }

    public void handleRequest(Request request) throws IOException {
        Request response = respond(request);
        if (response != null) {
            write(response);
        }
    }

    public void write(Request request) throws IOException {
        request.send(outputStream);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    private Request respond(Request request) throws IOException {
        Type type = request.getType();
        Topic topic = request.getTopic();
        Action action = request.getAction();
        Form form = request.getForm();

        Request res = new Request();
        res.setAction(action);
        res.setTopic(topic);
        Form resForm = new Form();
        res.setForm(resForm);
        // API
        switch (type) {
            case ASK: {
                switch (action) {
                    case READ:{
                        switch (topic){
                            case USER:
                                res.setType(Type.RES);
                                resForm.add("Connected Users", SocketServer.getConnectedUsers());
                                return res;
                        }
                        break;
                    }
                    case CREATE: {
                        switch (topic) {
                            case USER: { // User creation
                                res.setType(Type.RES);
                                String nickname = form.getValue("Nickname");
                                resForm.add("Nickname", nickname);
                                if (nickname != null && SocketServer.isNickUnique(nickname)) {
                                    UserManager userManager = new UserManagerImpl(nickname);
                                    SocketServer.addUser(userManager);
                                    this.user = userManager;
                                    System.out.println("user " + nickname + " created!");

                                    resForm.add("Verdict", true);

                                    Form broadForm = new Form();
                                    broadForm.add("Connected Users", SocketServer.getConnectedUsers());
                                    broadForm.add("Nickname", nickname);

                                    Request broadRequest = new Request(Type.BROAD, Action.CREATE, Topic.USER, broadForm);
                                    SocketServer.broadcast(broadRequest);
                                    return res;
                                } else {
                                    resForm.add("Verdict", false);
                                    resForm.add("Reason", "Nickname Taken");
                                    return res;
                                }
                            }
                            case MESSAGE: {
                                SocketServer.broadcast(request);
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case BROAD: {
                res.setType(Type.BROAD);
                resForm = form;
                return  res;
            }
        }
        return null;
    }
}
