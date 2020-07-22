package server.database;

import shared.wrappers.User;

public interface LogInDatabaseAccess {
    /**
     * Method which will be used in order to access database, when login and password are written and button Log In is pressed
     *
     * @return true, if nickname and password correspond to the nickname and password in database. Otherwise - false
     */
    boolean login(User user);
}
