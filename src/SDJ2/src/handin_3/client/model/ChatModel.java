package handin_3.client.model;


import handin_3.shared.Subject;


public interface ChatModel extends Subject {

    void sendMessage(String message);

    void sendMessage(String message, String... nicknames);

    void requestConnectedUsers();
}
