package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.BandItemViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import shared.utils.Views;

import java.io.File;
import java.net.MalformedURLException;

public class BandItemViewController extends ViewController {
    @FXML
    private Circle circle;
    @FXML
    public Label nameOfTheBand;
    private ViewHandler viewHandler;
    private BandItemViewModel bandItemViewModel;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        bandItemViewModel = viewModelFactory.getBandItemViewModel();
    }
    public void feed(File file, String bandName)
    {
        if(file == null)
        {
            circle.setFill(new ImagePattern(new Image(getClass().getResource("../../images/band_default.jpg").toString(), false)));
        }
        else
        {
            try {
                Image image = new Image(file.toURI().toURL().toExternalForm());
                circle.setFill(new ImagePattern(image));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        nameOfTheBand.setText(bandName);
    }

    public void onNameOfTheBandClicked(MouseEvent mouseEvent) {
        bandItemViewModel.feed(nameOfTheBand.getText());
        viewHandler.viewToPane(Views.BandProfile.name());
    }

    public void onCircleClicked(MouseEvent mouseEvent) {
        bandItemViewModel.feed(nameOfTheBand.getText());
        viewHandler.viewToPane(Views.BandProfile.name());
    }
}
