package handIn_2.client.view;

import handIn_2.client.viewModel.UserViewModel;
import handIn_2.client.core.ViewHandler;
import handIn_2.client.viewModel.ViewModel;
import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class UserViewController implements ViewController, PropertyChangeListener {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nicknameTextField;

    private ViewHandler viewHandler;
    private UserViewModel userViewModel;


    public void onSaveButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Save nickname btn pressed!");
        userViewModel.setNickName(nicknameTextField.getText());
    }

    @Override
    public void init(ViewModel viewModel, ViewHandler viewHandler) {
        this.userViewModel = (UserViewModel) viewModel;
        this.viewHandler = viewHandler;

        userViewModel.addListener(this);

        userViewModel.getNickStringProperty().bindBidirectional(nicknameTextField.textProperty());
        errorLabel.textProperty().bind(userViewModel.getErrorStringProperty());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Event event = Event.valueOf(evt.getPropertyName());

        switch (event) {
            case REQ_FROM_SERVER: {
                Request request = (Request) evt.getNewValue();
                Request.Type type = request.getType();
                Request.Action action = request.getAction();
                Request.Topic topic = request.getTopic();
                Form form = request.getForm();

                if (topic == Request.Topic.USER) {
                    if(type == Request.Type.RES){
                        if (form.getValue("Verdict") != null || form.getValue("Nickname") != null) {
                            boolean verdict = form.getValue("Verdict");
                            if (verdict) {
                                Platform.runLater(() -> {
                                    try {
                                        viewHandler.openView("ChatView", viewHandler.getVMFactory().getChatViewModel());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }
}
