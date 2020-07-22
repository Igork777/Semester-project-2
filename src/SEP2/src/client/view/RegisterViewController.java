package client.view;


import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.RegisterViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import shared.utils.Views;


public class RegisterViewController extends ViewController {
    private RegisterViewModel registerViewModel;
    private boolean isFirstTimeRegisterIsOpened = true;
    private ViewHandler viewHandler;

    @FXML
    private Label errorLabel;
    @FXML
    private Label signUpLabel;
    @FXML
    private Label backLabel;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private PasswordField passwordPWField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private ComboBox cityComboBox;

    /**
     * Initializes view model and binds nickname field, password field, date of birth
     * and city field to those in register view model.
     * Important notice: the class checks if there is the first time when view was opened
     * It is made in order to load all the possible cities from the database only once
     *
     * @param viewHandler view handler to swap between views
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.registerViewModel = viewModelFactory.getRegisterViewModel();
        this.viewHandler = viewHandler;
        nicknameTextField.textProperty().bindBidirectional(registerViewModel.nicknameProperty());
        passwordPWField.textProperty().bindBidirectional(registerViewModel.passwordProperty());
        dateOfBirthDatePicker.valueProperty().bindBidirectional(registerViewModel.dateOfBirthProperty());
        errorLabel.textProperty().bindBidirectional(registerViewModel.errorProperty());
        registerViewModel.cityProperty().bind(cityComboBox.valueProperty());
        //ADD LISTENERS HERE
        errorLabel.textProperty().setValue("Hello, don't hesitate to sign up!");
        if (isFirstTimeRegisterIsOpened) {
            cityComboBox.getItems().addAll(registerViewModel.getCities());
            isFirstTimeRegisterIsOpened = false;
        }
        Thread threadDownloadUser = new Thread(() ->
                ViewControllerFactory.getViewController(Views.UserProfile.name())
        );
        threadDownloadUser.start();
    }

    /**
     * Pressing Sign Up button triggers actions to register a user.
     * If sign up is not successful an Alert is showed with a message.
     * If the exception is not thrown - it means the registration is successful -> then we open new winodw
     * and clear all the fields
     */
    public void onClickSignUp(MouseEvent mouseEvent) {
        System.out.println(registerViewModel);
        if (registerViewModel.signUp()) {
            nicknameTextField.clear();
            passwordPWField.clear();
            dateOfBirthDatePicker.setValue(null);
            cityComboBox.setValue(null);
            viewHandler.setSidebar("Sidebar");
            viewHandler.viewToPane(Views.UserProfile.name());
        }
    }

    /**
     * Hovering over Sign up label the color of the font is changed to yellow
     */
    public void onMouseEnteredSignUp(MouseEvent mouseEvent) {
        signUpLabel.setStyle("-fx-text-fill: yellow");
    }

    /**
     * Upon exiting Sign up label the color of the font is changed to black.
     */
    public void onMouseExitedSignUp(MouseEvent mouseEvent) {
        signUpLabel.setStyle("-fx-text-fill: black");
    }

    /**
     * Pressing Label Back will put user back to First Window
     * All the text fields will be cleaned
     */
    public void onClickBack(MouseEvent mouseEvent) {
        viewHandler.viewToPane(Views.FirstWindow.name());
        nicknameTextField.clear();
        passwordPWField.clear();
        dateOfBirthDatePicker.setValue(null);
        cityComboBox.setValue(null);
    }

    /**
     * Hovering over Back label color of the font is changed to yellow
     */
    public void onMouseEnteredBack(MouseEvent mouseEvent) {
        backLabel.setStyle("-fx-text-fill: yellow");
    }

    /**
     * Upon exiting Back label the color of the font is changed to black.
     */
    public void onMouseExitedBack(MouseEvent mouseEvent) {
        backLabel.setStyle("-fx-text-fill: black");
    }
}
