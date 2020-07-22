package client.viewModel;

import client.model.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.utils.Checker;
import shared.wrappers.User;

/**
 * The class communicates with both view and model. Also it makes some logical validations
 */
public class LoginViewModel {

    /**
     * LoginModel variable is needed for making connection with the model
     * errorStringProperty is binned with the error label in the controller
     */
    private LoginModel model;
    private SimpleStringProperty error;
    private StringProperty loginName;
    private StringProperty loginPassword;

    /**
     * constructor, which assigns variable of type LoginModel to the local LoginModel
     * also. here errosStringProperty is initialized
     *
     * @param loginModel
     */
    public LoginViewModel(LoginModel loginModel) {
        this.model = loginModel;
        this.error = new SimpleStringProperty();
        this.loginName = new SimpleStringProperty();
        this.loginPassword = new SimpleStringProperty();
    }

    /**
     * In this class, firstly nickname and login went through format validation. If any of them fails,
     * the data will not be send to the server and false will be returned. Otherwise, data is sent to the       server and will be checked there.
     */
    public boolean login() {
        String result =  model.login(new User(loginName.getValue(), loginPassword.getValue()));
        error.setValue(result);
        if(result.startsWith("Welcome"))
        {
            loginName.setValue("");
            loginPassword.setValue("");
            return true;
        }
        return false;
    }

    /**
     * getter of the ErrorStringProperty
     * @return errorStringProperty
     */
    public SimpleStringProperty errorProperty() {
        return error;
    }

    /**
     * getter of the LoginNameProperty
     * @return login
     */
    public StringProperty loginNameProperty() {
        return loginName;
    }

    /**
     * getter of the PasswordProperty
     * @return password
     */
    public StringProperty passwordProperty() {
        return loginPassword;
    }
}
