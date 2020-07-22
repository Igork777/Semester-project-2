package server.manager;

import server.database.RegisterDatabaseAccess;
import server.database.RegisterDatabaseImpl;
import shared.wrappers.User;

import java.util.ArrayList;

public class RegisterManagerImpl implements RegisterManager {
    private RegisterDatabaseAccess registerDBAccess;

    public RegisterManagerImpl() {
        this.registerDBAccess = new RegisterDatabaseImpl();
    }

    /**
     * Method that returns the reply to inquiry to register the user
     *
     * @param user which has to be registered
     * @return message which will be represented in the view controller
     */

    @Override
    public String register(User user) {

        boolean loginNameAvailable = registerDBAccess.loginNameTaken(user.getLoginName());

        if (!loginNameAvailable) {
            return "Nickname already taken!";
        } else {
            if (registerDBAccess.register(user)) {
                return "Welcome aboard!";
            } else {
                System.out.println("wrong");
                return "Something went wrong!";
            }
        }
    }
}
