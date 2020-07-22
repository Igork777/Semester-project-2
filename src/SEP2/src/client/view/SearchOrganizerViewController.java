package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.SearchOrganizerViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.utils.Views;
import shared.wrappers.Organizer;


public class SearchOrganizerViewController extends ViewController {
    @FXML private TextField fullNameTextField;
    @FXML private ComboBox<String> regionSelector;
    @FXML private TableView<Organizer> organizerResultTable;
    @FXML private TableColumn<Organizer, String> organizerResultFullName;
    @FXML private TableColumn<Organizer, String> organizerResultCity;

    private boolean isFirstTime = true;

    private SearchOrganizerViewModel searchOrganizerViewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.searchOrganizerViewModel = viewModelFactory.getSearchOrganizerViewModel();

        if (isFirstTime) {
            regionSelector.getItems().add(0, null);
            regionSelector.getItems().addAll(searchOrganizerViewModel.getRegions());
            isFirstTime = false;
        }
        fullNameTextField.textProperty().bindBidirectional(searchOrganizerViewModel.fullNameProperty());
        regionSelector.valueProperty().bindBidirectional(searchOrganizerViewModel.regionProperty());

        organizerResultFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        organizerResultCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    public void search()
    {
        organizerResultTable.getItems().clear();
        searchOrganizerViewModel.search();
        showResults();
    }

    private void showResults()
    {
        ObservableList<Organizer> list = searchOrganizerViewModel.getSearchResultOrganizers();
        organizerResultTable.setItems(list);
    }

    public void resultClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            int orgId = organizerResultTable.getSelectionModel().getSelectedItem().getUserId();
            searchOrganizerViewModel.organizerProfileOf(orgId);
            viewHandler.viewToPane(Views.OrganizerProfile.name());
        }
    }
}
