package handin_3.client.view;

import handin_3.client.core.ViewHandler;
import handin_3.client.viewModel.UserViewModel;
import handin_3.client.viewModel.ViewModel;
import handin_3.shared.Event;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class UserViewController implements ViewController {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nicknameTextField;

    private ViewHandler viewHandler;
    private UserViewModel userViewModel;


    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Save nickname btn pressed!");

        userViewModel.register(nicknameTextField.getText());
    }

    @Override
    public void init(ViewModel viewModel, ViewHandler viewHandler) {
        this.userViewModel = (UserViewModel) viewModel;
        this.viewHandler = viewHandler;

        this.userViewModel.addListener(Event.RegistrationIsSuccessful.name(), this::onRegistrationIsSuccessful);
        userViewModel.getNickStringProperty().bindBidirectional(nicknameTextField.textProperty());
        errorLabel.textProperty().bind(userViewModel.getErrorStringProperty());
    }

    private void onRegistrationIsSuccessful(PropertyChangeEvent propertyChangeEvent) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    viewHandler.openView("ChatView", viewHandler.getVMFactory().getChatViewModel());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
