package client.networking;

import shared.wrappers.User;

public interface LoginClient
{
    /**
     * method checks if user inputted correct nickname and password or not
     * @return true if nickname if nickname and password correspond to the data written in database, otherwise false is returned
     */
    boolean login(User user);
}
