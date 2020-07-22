package client.model;

import shared.wrappers.User;

public interface LoginModel
{
    /**
     * method checks if user inputted correct nickname and password or not
     * @return true if nickname if nickname and password correspond to the data written in database, otherwise false is returned
     */
    String login(User user);
}
