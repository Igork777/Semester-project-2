package handIn_2.client.view;

import handIn_2.client.core.ViewHandler;
import handIn_2.client.viewModel.ChatViewModel;
import handIn_2.client.viewModel.ViewModel;
import handIn_2.shared.Event;
import handIn_2.shared.Form;
import handIn_2.shared.Request;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ChatViewController implements ViewController, PropertyChangeListener {
    @FXML
    private TextField messageTextField;
    @FXML
    private TableView<String> usersTable;
    @FXML
    private TableColumn<String, String> userColumn;
    @FXML
    private TextArea allMessagesTextArea;

    @FXML
    private TextField userNameTextField;

    private ChatViewModel viewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModel vm, ViewHandler vh) {
        viewModel = (ChatViewModel) vm;
        viewHandler = vh;

        viewModel.addListener(this);

        userNameTextField.textProperty().bind(viewModel.getUserNameStringProperty());
        viewModel.getMessageStringProperty().bindBidirectional(messageTextField.textProperty());
        allMessagesTextArea.textProperty().bind(viewModel.getAllMessagesTextAreaStringAreaProperty());
        allMessagesTextArea.setWrapText(true);


        messageTextField.setFocusTraversable(true);
        messageTextField.requestFocus();

        userColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        viewModel.requestConnectedUsers();
    }

    public void onSend(ActionEvent actionEvent) {
        viewModel.sendMessage(messageTextField.textProperty().getValue());
        allMessagesTextArea.setScrollTop(Double.MAX_VALUE);
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Event event = Event.valueOf(evt.getPropertyName());

        Request request = (Request) evt.getNewValue();
        Form form = request.getForm();
        switch (event) {
            case BROAD_FROM_SERVER:
                switch (request.getTopic()) {
                    case USER:
                        switch (request.getAction()) {
                            case CREATE:
                            case READ:
                            case DELETE:
                                switch (request.getType()){
                                    case BROAD:
                                    case RES:{
                                        usersTable.getItems().clear();
                                        ArrayList<String> connectedUsers = form.getValue("Connected Users");
                                        usersTable.getItems().addAll(connectedUsers);
                                        break;
                                    }
                                }
                                break;
                        }
                }
        }
    }
}
