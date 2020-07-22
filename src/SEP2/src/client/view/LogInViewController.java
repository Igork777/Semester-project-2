package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import server.database.SearchBandDBImpl;
import shared.utils.Views;


import java.io.IOException;

public class LogInViewController extends ViewController {

    /**
     * FXML fields
     */
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label backId;
    @FXML
    private Label logInIdBack;

    private ViewHandler viewHandler;
    private LoginViewModel viewModel;

    /**
     * method initializes viewModel and viewHandler
     * Binding is made for communication of viewModel with model
     * and default greeting message is added
     *
     * @param viewModelFactory parameter which will be used to retrieve the needed VM
     * @param viewHandler      parameter which will be assigned to local variable viewHandler
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModelFactory.getLogInViewModel();
        nicknameTextField.textProperty().bindBidirectional(viewModel.loginNameProperty());
        passwordTextField.textProperty().bindBidirectional(viewModel.passwordProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.errorProperty());
        errorLabel.textProperty().setValue("Hey stranger! Log in to start rockin'!");
        Thread threadDownloadUser = new Thread(new Runnable() {
            @Override
            public void run() {
                ViewControllerFactory.getViewController(Views.UserProfile.name());
            }
        });
        Thread threadStartMusicianView = new Thread(new Runnable() {
            @Override
            public void run() {
                ViewControllerFactory.getViewController(Views.MusicianView.name());
            }
        });
        threadStartMusicianView.start();
        threadDownloadUser.start();
    }

    /**
     * method which opens First window view
     * all the text fields will be cleared
     *
     * @param mouseEvent event, that happens when I click the label Back
     * @throws IOException can be thrown by method open()
     */
    public void onClickBack(MouseEvent mouseEvent) throws IOException {
        viewHandler.viewToPane(Views.FirstWindow.name());
        nicknameTextField.clear();
        passwordTextField.clear();
    }

    /**
     * method which changes the color of the Back label and changes cursor to hand
     *
     * @param mouseEvent event which happens when user hovers on Back label
     */
    public void onMouseEnteredBack(MouseEvent mouseEvent) {
        backId.setStyle("-fx-text-fill: yellow");
    }

    /**
     * method which changes the color of the Back label to previous and changes cursor to arrow
     *
     * @param mouseEvent event which happens when user doesn't hover on Back label anymore
     */
    public void onMouseExitedBack(MouseEvent mouseEvent) {
        backId.setStyle("-fx-text-fill: black");
    }


    /**
     * method which changes the color of the Log In label and changes cursor to hand
     *
     * @param mouseEvent event which happens when user hovers on Log In label
     */
    public void onMouseEnteredLogIn(MouseEvent mouseEvent) {
        logInIdBack.setStyle("-fx-text-fill: yellow");
    }

    /**
     * method which changes the color of the Log In label to previous and changes cursor to arrow
     *
     * @param mouseEvent event which happens when user doesn't hover on Log In label anymore
     */
    public void onMouseExitedLogIn(MouseEvent mouseEvent) {
        logInIdBack.setStyle("-fx-text-fill: black");
    }

    /**
     * This method reads data from both text fields, and after that it launches the validation method
     * which firstly will check the format of the nickname and password and if it is ok, this data
     * will be send for comparing with database notes
     * If user successfully logged in -> then all the text fields will be cleared, sidebar will be appended and new UserProfile window will be opened
     *
     * @param mouseEvent event happens when user clicks on Log in label
     */
    public void onClickLogIn(MouseEvent mouseEvent) {
        if (viewModel.login())
        {
            viewHandler.setSidebar("Sidebar");
            viewHandler.viewToPane(Views.UserProfile.name());
        }
    }
}
