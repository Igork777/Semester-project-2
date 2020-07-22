package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.SearchBandViewModel;
import client.viewModel.SearchMusicianViewModel;
import client.viewModel.SearchOrganizerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewController extends ViewController {
    @FXML
    private ComboBox<String> searchFor;
    @FXML
    private AnchorPane criteriaPane;

    private ViewHandler viewHandler;
    private boolean initialized;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {

        this.viewHandler = viewHandler;
        if(!initialized){
            searchFor.getItems().addAll("Musician", "Organizer", "Band");
            initialized = true;
        }


    }

    public void onSearchForChanged(ActionEvent actionEvent){
        String selectedOption = searchFor.getSelectionModel().getSelectedItem()+"";
        System.out.println(selectedOption);
        viewHandler.viewToPane("Search"+selectedOption+"View", criteriaPane, false);
    }

}
