package client.model;

import client.networking.RegisterClient;
import shared.utils.Checker;
import shared.wrappers.User;

public class RegisterModelImpl implements RegisterModel {
    private RegisterClient client;
    private Checker checker;
    /**
     * Assigns fields at instance initialization
     *
     * @param registerClient assigns register client from client factory
     */
    public RegisterModelImpl(RegisterClient registerClient) {
        checker = new Checker();
        client = registerClient;
    }

    /**
     * Attempts to register a user
     * In case the registration was successful saves the user info  in LocalStorage
     *
     * @return "Welcome aboard!" message if registration was successful, else error message of what may have gone wrong
     */
    @Override
    public String register(User user) {
        if (user.getLoginName() == null || user.getLoginName().equals("")) {
            return "Nickname can't be empty!";
        } else if (user.getLoginName().replace(" ", "").length() < Checker.MIN_LENGTH_NAME) {
            return "Nickname is too short!";
        } else if (user.getLoginName().length() > Checker.MAX_LENGTH_NAME) {
            return "Nickname is too long!";
        } else if (!(checker.isValidName(user.getLoginName()))) {
            return "Invalid character in nickname!";
        } else if (user.getPassword() == null || user.getPassword().equals("")) {
            return "You can't create account without the password!";
        } else if (user.getPassword().replace(" ", "").length() < Checker.MIN_LENGTH_PASSWORD) {
            return "Password is too short!";
        } else if (user.getPassword().length() > Checker.MAX_LENGTH_PASSWORD) {
            return "Password is too long!";
        } else if (!(checker.isValidPassword(user.getPassword()))) {
            return "Invalid character in password!";
        } else if (user.getDateOfBirth() == null) {
            return "Invalid date of birth!";
        }
        String result = client.register(user);
        if (result.equals("Welcome aboard!")) {
            LocalStorageImpl.getInstance().setCurrentUserByNickname(user.getLoginName());
        }
        return result;
    }

}
