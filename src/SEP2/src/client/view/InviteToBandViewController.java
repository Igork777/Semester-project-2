package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.InviteToBandViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import shared.wrappers.Musician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class InviteToBandViewController extends ViewController {
    @FXML
    private Pane paneIds;
    @FXML
    private Label resultAddingToTheBand;
    private Musician musicianSelected;
    private ViewHandler viewHandler;
    private String nameCurrentBand;
    private InviteToBandViewModel inviteToBandViewModel;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
        inviteToBandViewModel = viewModelFactory.getInviteToBandViewModel();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchMusicianView.fxml"));
        try {
            paneIds.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchMusicianViewController searchMusicianViewController = ((SearchMusicianViewController) fxmlLoader.getController());
        searchMusicianViewController.init(viewModelFactory,viewHandler);
        searchMusicianViewController.addListener("Musician selection changed", this::musicianSelectionChanged);
        resultAddingToTheBand.textProperty().bind(inviteToBandViewModel.simpleStringPropertyProperty());
    }

    private void musicianSelectionChanged(PropertyChangeEvent propertyChangeEvent) {
        musicianSelected = (Musician) propertyChangeEvent.getNewValue();
    }

    public String getCurrentBand() {
        return nameCurrentBand;
    }

    public void setCurrentBand(String currentBand) {
        this.nameCurrentBand = currentBand;
    }

    public void onClickAdd(MouseEvent mouseEvent) {
        inviteToBandViewModel.inviteToBand(musicianSelected, nameCurrentBand);
        if(resultAddingToTheBand.getText().startsWith("Musician"))
        {
            resultAddingToTheBand.setStyle("-fx-text-fill: green");
        }
        else
        {
            resultAddingToTheBand.setStyle("-fx-text-fill: red");
        }
    }
}
