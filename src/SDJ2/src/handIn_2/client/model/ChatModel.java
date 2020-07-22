package handIn_2.client.model;

import handIn_2.shared.Form;

public interface ChatModel extends Model {

    void sendMessage(Form form);

    void requestConnectedUsers();
}
