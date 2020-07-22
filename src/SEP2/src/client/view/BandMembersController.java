package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.BandMembersViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import shared.wrappers.Musician;

public class BandMembersController extends ViewController {

    public Button kickButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TableColumn<Musician, String> cityColumn;
    @FXML
    private TableColumn<Musician, String> nameColumn;
    @FXML
    private TableView<Musician> table;
    private ViewHandler viewHandler;
    private BandMembersViewModel bandMembersViewModel;

    public void onKickButton(MouseEvent mouseEvent)
    {
        bandMembersViewModel.removeMusicianFromBand(table.getSelectionModel().getSelectedItem());
        table.getItems().clear();
        table.getItems().addAll(bandMembersViewModel.getMusiciansInTheBand());
        if(errorLabel.getText().startsWith("Musician"))
        {
            errorLabel.setStyle("-fx-text-fill: green");
        }
        else
        {
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler)
    {
           this.viewHandler = viewHandler;
           bandMembersViewModel = viewModelFactory.getBandMembersViewModel();
           cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
           nameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
           table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getItems().addAll(bandMembersViewModel.getMusiciansInTheBand());
        errorLabel.textProperty().bind(bandMembersViewModel.removalOfTheMusicianProperty());
        if(!(bandMembersViewModel.checkIfMusicianIsBandAdministrator()))
        {
            kickButton.setDisable(true);
            kickButton.setVisible(false);
        }

    }
}
