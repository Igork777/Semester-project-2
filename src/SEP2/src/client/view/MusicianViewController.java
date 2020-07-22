package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.MusicianViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import shared.utils.Views;
import shared.wrappers.Instrument;

public class MusicianViewController extends ViewController
{
    @FXML
    private Label nickNameId;
    @FXML
    private Label dateOfBirthId;
    @FXML
    private Label cityId;
    @FXML
    private Label regionId;
    @FXML
    private TableView<Instrument> tableInstrumentsId;
    @FXML
    private TableColumn<String, Instrument> instrumentColumn;
    @FXML
    private TableColumn<Integer, Instrument> expertiseColumn;
    @FXML
    private ImageView iconMusicianId;
    @FXML
    private ScrollPane scrollPaneId;
    @FXML
    private Label bioId;
    private ViewHandler viewHandler;
    private MusicianViewModel musicianViewModel;
    @FXML
    private Text text = new Text();
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
        musicianViewModel = viewModelFactory.getMusicianViewModel();
        nickNameId.textProperty().bind(musicianViewModel.nickNameProperty());
        dateOfBirthId.textProperty().bind(musicianViewModel.dateOfBirthProperty());
        regionId.textProperty().bind(musicianViewModel.regionProperty());
        cityId.textProperty().bind(musicianViewModel.cityProperty());
        text.textProperty().bind(musicianViewModel.bioProperty());
        text.wrappingWidthProperty().bind(scrollPaneId.widthProperty());
       scrollPaneId.setContent(text);
        instrumentColumn.setCellValueFactory(new PropertyValueFactory<>("instrument"));
        expertiseColumn.setCellValueFactory(new PropertyValueFactory<>("expertise"));
        musicianViewModel.feed();
        tableInstrumentsId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableInstrumentsId.getItems().clear();
        tableInstrumentsId.setItems(musicianViewModel.getInstruments());
        new Thread(new Runnable() {
            @Override
            public void run() {
                ViewControllerFactory.getViewController(Views.CreateBand.name());
            }
        }).start();
    }

    //Me trying to work with the pictures
    public void onPictureClicked(MouseEvent mouseEvent)
    {

    }
}
