package handIn_2.client.networking;

import handIn_2.shared.Request;
import handIn_2.shared.Subject;

import java.beans.PropertyChangeListener;
import java.io.IOException;

public interface Client extends Runnable, PropertyChangeListener, Subject
{
    void write(Request request) throws IOException;
    Request read() throws IOException, ClassNotFoundException;
}
