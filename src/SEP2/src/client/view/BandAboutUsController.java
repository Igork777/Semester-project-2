package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.BandAboutUsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class BandAboutUsController extends ViewController {
    @FXML
    private TextArea textAreaId;
    @FXML
    private Text textGenre;
    @FXML
    private Text textEmail;
    @FXML
    private Text textTelephone;
    @FXML
    private Text textFoundationDate;
    @FXML
    private Text textCity;
    @FXML
    private Text textRegion;


    private ViewHandler viewHandler;
    private BandAboutUsViewModel bandAboutUsViewModel;
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler)
    {
        textAreaId.clear();
        this.viewHandler = viewHandler;
        bandAboutUsViewModel = viewModelFactory.getBandAboutUsViewModel();
        bandAboutUsViewModel.feed();
        textEmail.textProperty().bind(bandAboutUsViewModel.emailProperty());
        textTelephone.textProperty().bind(bandAboutUsViewModel.telephoneProperty());
        textFoundationDate.textProperty().bind(bandAboutUsViewModel.foundationDateProperty());
        textCity.textProperty().bind(bandAboutUsViewModel.cityProperty());
        textRegion.textProperty().bind(bandAboutUsViewModel.regionProperty());
        textGenre.textProperty().bind(bandAboutUsViewModel.genreProperty());

        textAreaId.appendText(bandAboutUsViewModel.getBio());
    }
}
