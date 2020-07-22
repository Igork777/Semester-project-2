package client.model;

import client.networking.LoginClient;
import shared.utils.Checker;
import shared.wrappers.User;

import java.util.HashMap;

/**
 * LoginModelImpl class, which has all the log in logic on the client side
 */
public class LoginModelImpl implements LoginModel {
    /**
     * field, through which class has reference to the LogInClient
     */
    private LoginClient client;
    private Checker checker;

    /**
     * Constructor, which assigns parameter of type LoginClient to the local variable of the type LoginClient
     *
     * @param loginClient will be assigned to local field
     */
    public LoginModelImpl(LoginClient loginClient) {
        this.client = loginClient;
        checker = new Checker();
    }

    /**
     * method, which calls another method login() on LoginClient, which returns true or false based on
     * user input
     *
     * @return true if nickname if nickname and password correspond to the data written in database, otherwise false is returned
     */
    @Override
    public String login(User user) {

        if (user.getLoginName() == null || user.getLoginName().equals("") || user.getLoginName().replace(" ", "").length() < Checker.MIN_LENGTH_NAME || user.getLoginName().length() > Checker.MAX_LENGTH_NAME || !(checker.isValidName(user.getLoginName()))) {
            return "Invalid niсkname";
        } else if (user.getPassword() == null || user.getPassword().equals("") || user.getPassword().replace(" ", "").length() < Checker.MIN_LENGTH_PASSWORD || user.getPassword().length() > 30 | !(checker.isValidPassword(user.getPassword()))) {
            return "Invalid password";
        }
        if (client.login(user)) {
            User currentUser = LocalStorageImpl.getInstance().setCurrentUserByNickname(user.getLoginName());
            LocalStorageImpl.getInstance().fetchMusicianInfo(currentUser.getUserId());
            LocalStorageImpl.getInstance().fetchPlayedInstruments(currentUser.getUserId());
            return "Welcome aboard";
        } else {
            return "Wrong nickname or password";
        }
    }
}
