package handin_3.client.view;

import handin_3.client.core.ViewHandler;
import handin_3.client.viewModel.ChatViewModel;
import handin_3.client.viewModel.ViewModel;
import handin_3.shared.Event;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ChatViewController implements ViewController {
    @FXML
    public Label userNameLabel;
    @FXML
    private TextField messageTextField;
    @FXML
    private TableView<String> usersTable;
    @FXML
    private TableColumn<String, String> userColumn;
    @FXML
    private TextArea allMessagesTextArea;


    private ChatViewModel viewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModel vm, ViewHandler vh) {
        viewModel = (ChatViewModel) vm;
        viewHandler = vh;

        viewModel.addListener(Event.AllNicknamesAreReceived.name(), this::onAllNicknamesAreReceived);
        userNameLabel.textProperty().bind(viewModel.getUserNameStringProperty());
        viewModel.getMessageStringProperty().bindBidirectional(messageTextField.textProperty());
        allMessagesTextArea.textProperty().bind(viewModel.getAllMessagesTextAreaStringAreaProperty());
        allMessagesTextArea.setWrapText(true);


        messageTextField.setFocusTraversable(true);
        messageTextField.requestFocus();

        userColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        viewModel.requestConnectedUsers();
    }

    private void onAllNicknamesAreReceived(PropertyChangeEvent propertyChangeEvent)
    {
        usersTable.getItems().clear();
        usersTable.getItems().addAll((ArrayList<String>)propertyChangeEvent.getNewValue());
    }

    public void onSend(ActionEvent actionEvent)
    {
        viewModel.sendMessage(messageTextField.textProperty().getValue());
        messageTextField.setText("");
        allMessagesTextArea.setScrollTop(Double.MAX_VALUE);
    }

}
