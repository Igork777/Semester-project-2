package client.viewModel;

import client.model.CityModel;
import client.model.RegisterModel;
import javafx.beans.property.*;
import shared.wrappers.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterViewModel {
    private RegisterModel registerModel;
    private CityModel cityModel;

    private StringProperty error;
    private StringProperty nickname;
    private StringProperty password;
    private ObjectProperty<LocalDate> dateOfBirth;
    private StringProperty city;

    /**
     * Initializes the StringProperties bound to RegisterController
     *
     * @param registerModel register model received from model factory
     */
    public RegisterViewModel(RegisterModel registerModel, CityModel cityModel) {
        this.registerModel = registerModel;
        this.cityModel = cityModel;
        //initiating properties
        nickname = new SimpleStringProperty();
        password = new SimpleStringProperty();
        dateOfBirth = new SimpleObjectProperty<>();
        city = new SimpleStringProperty();
        error = new SimpleStringProperty();
    }

    /**
     * Attempts to register a user if the user data is present
     * Before sending the data to the server, some checks of the format will be executed
     *
     * @return true if user was successfully registered; false if the user was not registered or the user data was not in abeyance with rules
     */
    public boolean signUp() {
        String string = registerModel.register(new User(nickname.getValue(), password.getValue(), dateOfBirth.getValue(), city.getValue()));
        error.setValue(string);
        return string.equals("Welcome aboard!");

    }

    /**
     * Gets nickname String property
     *
     * @return StringProperty nickname
     */
    public StringProperty nicknameProperty() {
        return nickname;
    }

    /**
     * Gets password String property
     *
     * @return StringProperty password
     */
    public StringProperty passwordProperty() {
        return password;
    }

    /**
     * Gets date of birth Object property
     *
     * @return ObjectProperty<LocalDate> date of birth
     */
    public Property<LocalDate> dateOfBirthProperty() {
        return dateOfBirth;
    }

    /**
     * Gets city String property
     *
     * @return StringProperty city
     */
    public StringProperty cityProperty() {
        return city;
    }

    /**
     * Gets error label property
     *
     * @return StringProperty errorLabel
     */
    public StringProperty errorProperty() {
        return error;
    }

    /**
     * Gets cities
     *
     * @return cities from the database
     */
    public ArrayList<String> getCities() {
        return cityModel.getCities();
    }
}
