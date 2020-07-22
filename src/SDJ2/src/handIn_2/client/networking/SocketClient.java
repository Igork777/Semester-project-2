package handIn_2.client.networking;

import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;
import handIn_2.shared.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient implements Client {

    private PropertyChangeSupport support;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public SocketClient() throws IOException {
        this.socket = new Socket("localhost", 2910);
        outputStream = new ObjectOutputStream(this.socket.getOutputStream());
        inputStream = new ObjectInputStream(this.socket.getInputStream());
        support = new PropertyChangeSupport(this);
        System.out.println("Socket client ready...");
    }

    @Override
    public void write(Request request) throws IOException {
        request.send(outputStream);
    }

    @Override
    public Request read() throws IOException, ClassNotFoundException {
        return (Request) inputStream.readObject();
    }

    public void handleRequest(Request request) throws IOException {
        support.firePropertyChange(Event.REQ_FROM_SERVER.name(), null, request);
    }

    @Override
    public void run() {
        try {
            System.out.println("Client running!");
            while (true) {
                handleRequest(read());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        Event event;
        try {
            event = Event.valueOf(evt.getPropertyName());
        } catch (Exception e) {
            System.out.println("Event " + evt.getPropertyName() + " is not found");
            return;
        }

        if (event == Event.REQ_FROM_CLIENT) {
            Request request = (Request) evt.getNewValue();
            try {
                write(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}