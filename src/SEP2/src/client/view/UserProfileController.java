package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.UserViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class UserProfileController extends ViewController {
    @FXML
    private Label nameId;
    @FXML
    private Label cityId;
    @FXML
    private Label dateOfBirthId;
    @FXML
    private Label regionId;


    private ViewHandler viewHandler;
    private UserViewModel userViewModel;

    /**
     * The method which assigns viewModelFactory and viewHandler
     * to the local variables
     * Also text fields from view layer bind with the text properties from the view model layer
     * @param viewModelFactory parameter, in order to have the connection with the model layer
     * @param viewHandler parameter, in order to change views
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
        userViewModel = viewModelFactory.getUserViewModel();
        nameId.textProperty().bind(userViewModel.nameProperty());
        cityId.textProperty().bind(userViewModel.cityProperty());
        dateOfBirthId.textProperty().bind(userViewModel.dateOfBirthProperty());
        regionId.textProperty().bind(userViewModel.regionProperty());
        userViewModel.feed();
    }
}
