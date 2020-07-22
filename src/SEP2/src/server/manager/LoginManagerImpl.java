package server.manager;

import server.database.LogInDatabaseAccess;
import server.database.LogInDatabaseImpl;
import shared.wrappers.User;

/**
 * Class is made in order to make some logic on the server side
 */
public class LoginManagerImpl implements LoginManager {
    /**
     * field which has a reference to the LogInDatabaseAccess field
     */
    private LogInDatabaseAccess logInDBAccess;

    /**
     * logInDB access is create in the constructor of LoginManagerImpl
     */
    public LoginManagerImpl() {
        this.logInDBAccess = new LogInDatabaseImpl();
    }

    /**
     * Method, which calls method login() on LogInDBAcces
     *
     * @return true if nickname and password match with the note in Database, otherwise false is returned
     */
    @Override
    public boolean login(User user) {
        return logInDBAccess.login(user);
    }

}
