package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import shared.utils.Views;

public class CreateOrOpenBandController extends ViewController {
    @FXML
    private Label createBandId;
    @FXML
    private Label myBandsId;
    private ViewHandler viewHandler;

    public void onCreateBandClicked(MouseEvent mouseEvent) {
        viewHandler.viewToPane(Views.CreateBand.name());
    }

    public void onCreateBandEntered(MouseEvent mouseEvent) {
        createBandId.setStyle("-fx-text-fill: yellow");
    }

    public void onMyBandsClicked(MouseEvent mouseEvent) {
        viewHandler.viewToPane(Views.MyBands.name());
    }

    public void onCreateBandExited(MouseEvent mouseEvent) {
        createBandId.setStyle("-fx-text-fill: white");
    }

    public void onMyBandsEnter(MouseEvent mouseEvent) {
        myBandsId.setStyle("-fx-text-fill: yellow");
    }

    public void onMyBandsExited(MouseEvent mouseEvent) {
        myBandsId.setStyle("-fx-text-fill: white");
    }

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }
}
