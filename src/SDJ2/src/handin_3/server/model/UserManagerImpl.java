package handin_3.server.model;

import handin_3.shared.ClientCallBack;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerImpl implements UserManager {
    private static HashMap<String, ClientCallBack> users = Storage.getUsersDatabase();


    @Override
    public void registerUser(String nickname, ClientCallBack client) {
        try {
            if (!isUnique(nickname)) {
                client.registerUserCallbackFailure(nickname);
            } else {
                client.registerUserCallbackSuccess(nickname);
                userJoined(nickname);
                users.put(nickname, client);
                broadcastUsersList();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUser(ClientCallBack client) {
        String nickname = getNickNameOfTheClient(client);
        users.remove(nickname);
        userLeft(nickname);
        broadcastUsersList();
    }

    public void getAllNicknames(ClientCallBack client) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(users.keySet());
        try {
            client.getAllNicknamesCallback(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void broadcastUsersList() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(users.keySet());
        for (ClientCallBack c : users.values()) {
            try {
                c.getAllNicknamesCallback(list);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isUnique(String string) {
        return !(users.containsKey(string));
    }

    private String getNickNameOfTheClient(ClientCallBack client) {
        String nickName = "";
        for (Map.Entry<String, ClientCallBack> entry : users.entrySet()) {
            if (entry.getValue().equals(client)) {
                nickName = entry.getKey();
                return nickName;
            }
        }
        try {
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            System.out.println("User didn't log in");
        }
        return nickName;
    }

    private void userJoined(String nickname) {
        for (ClientCallBack client : users.values()) {
            try {
                client.userJoinedPushNotification(nickname);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void userLeft(String nickname) {
        ArrayList<String> nicknames = new ArrayList<>();
        nicknames.addAll(users.keySet());
        for (ClientCallBack client : users.values()) {
            try {
                client.getAllNicknamesCallback(nicknames);
                client.userLeftPushNotification(nickname);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
