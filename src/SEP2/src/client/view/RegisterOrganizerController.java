package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.RegisterOrganizerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import shared.utils.Views;

public class RegisterOrganizerController extends ViewController
{
	@FXML private TextField emailTextField;
  @FXML private TextField telNoTextField;
  @FXML private Label errorLabel;
  @FXML private TextField fullNameTextField;
  @FXML private TextArea biographyTextField;


  private RegisterOrganizerViewModel registerOrganizerViewModel;
  private ViewHandler viewHandler;


  /**
   * Initializes view model and binds full name field and biography field to those in RegisterOrganizer view model.
   * @param viewModelFactory parameter, in order to have the connection with the model layer
   * @param viewHandler parameter, in order to change views
   */

  @Override public void init(ViewModelFactory viewModelFactory,
      ViewHandler viewHandler)
  {
      this.registerOrganizerViewModel = viewModelFactory.getRegisterOrganizerViewModel();
      this.viewHandler = viewHandler;
      fullNameTextField.textProperty().bindBidirectional(registerOrganizerViewModel.fullNameProperty());
      biographyTextField.textProperty().bindBidirectional(registerOrganizerViewModel.biographyProperty());
      errorLabel.textProperty().bindBidirectional(registerOrganizerViewModel.errorProperty());
      telNoTextField.textProperty().bindBidirectional(registerOrganizerViewModel.telNoProperty());
      emailTextField.textProperty().bindBidirectional(registerOrganizerViewModel.emailProperty());
  }

  /**
   * Attempts to register the user as an organizer and clears the fields if the registration is successful
   * @param actionEvent - the event that triggers when user presses the register button
   */
  public void onRegisterOrganizerButton(ActionEvent actionEvent)
  {
    if(registerOrganizerViewModel.registerOrganizer())
    {
      fullNameTextField.clear();
      biographyTextField.clear();
      viewHandler.viewToPane(Views.OrganizerProfile.name());
    }
  }

}
