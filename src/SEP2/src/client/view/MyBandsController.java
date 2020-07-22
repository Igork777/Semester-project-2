package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.MyBandsViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import shared.utils.Views;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MyBandsController extends ViewController {
    @FXML
    private VBox paneForViews;
    private HashMap<String, File> bands;
    private MyBandsViewModel myBandsViewModel;
    private ViewHandler viewHandler;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
        myBandsViewModel = viewModelFactory.getMyBandsViewModel();
        bands = myBandsViewModel.getAllBandsMusicianIsIn();
        paneForViews.getChildren().clear();
            for (Map.Entry<String, File> band : bands.entrySet())
            {
                BandItemViewController bandItemViewController = (BandItemViewController) ViewControllerFactory.getNewViewController(Views.BandItemView.name());
                bandItemViewController.init(viewModelFactory, viewHandler);
                bandItemViewController.feed(band.getValue(),band.getKey());
                viewHandler.rootToPane(bandItemViewController.getRoot(), paneForViews);
            }


    }
}
