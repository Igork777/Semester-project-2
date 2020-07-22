package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.SearchMusicianViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import shared.utils.Subject;
import shared.utils.Views;
import shared.wrappers.Musician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchMusicianViewController  extends ViewController implements PropertyChangeListener, Subject {
    @FXML
    private TextField expertiseField;
    @FXML
    private ComboBox<String> instrumentSelector;
    @FXML
    private ComboBox<String> regionSelector;
    @FXML
    private TextField artistNameField;
    @FXML
    private VBox instrumentsPane;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Musician> musiciansTableView;
    @FXML
    private TableColumn<Musician, String> artistNameCol;
    @FXML
    private TableColumn<String, String> artistCityCol;


    private ViewModelFactory viewModelFactory;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private SearchMusicianViewModel searchMusicianViewModel;
    private ViewHandler viewHandler;
    private boolean initialized;

    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        if(!initialized){
            this.viewHandler = viewHandler;
            this.viewModelFactory = viewModelFactory;
            this.searchMusicianViewModel = viewModelFactory.getSearchMusicianViewModel();
            this.instrumentSelector.getItems().addAll(Musician.getInstrumentsId().values());
            // TODO Fetch regions and put them in the combobox;
            initialized = true;
            artistNameCol.setCellValueFactory(new PropertyValueFactory<>("nickname"));
            artistCityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
            regionSelector.getItems().addAll(null, "Nordjylland", "Midtjylland", "Syddanmark", "Sjælland", "Hovedstaden");
        }
    }

    public void addInstrument(MouseEvent mouseEvent) {
        String instrument = instrumentSelector.getValue();
        boolean instrumentAdded = false;
        String exp = expertiseField.getText().replaceAll("[a-zA-Z+\\-=!@#$%&*()_`~?/\\\\|^]+", "");
        try {
            instrumentAdded = searchMusicianViewModel.addInstrument(instrument, exp);
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
        if(instrumentAdded){
            InstrumentViewController instrumentViewController = (InstrumentViewController) ViewControllerFactory.getNewViewController("InstrumentView");
            instrumentViewController.init(viewModelFactory, viewHandler);
            instrumentViewController.addListener(this);
            instrumentViewController.feed(instrument, Math.round(Float.parseFloat(exp)));
            viewHandler.rootToPane(instrumentViewController.getRoot(), instrumentsPane);
            instrumentSelector.valueProperty().set(null);
            expertiseField.setText("");
            errorLabel.setText("");
        }
    }

    public void search(){
        ObservableList<Musician> musicianObservableList = FXCollections.observableArrayList(searchMusicianViewModel.search(artistNameField.getText(), regionSelector.getSelectionModel().getSelectedItem()));
        System.out.println(musicianObservableList.size());
        musiciansTableView.setItems(musicianObservableList);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String event = evt.getPropertyName();
        if (event.equals("Delete Instrument") && evt.getNewValue() instanceof InstrumentViewController) {
            InstrumentViewController instrumentToDel = (InstrumentViewController) evt.getNewValue();
            String instrumentName = instrumentToDel.getInstrument();
            boolean instrumentRemoved = searchMusicianViewModel.removeInstrument(instrumentName);
            if (instrumentRemoved) {
                instrumentsPane.getChildren().remove(instrumentToDel.getRoot());
                instrumentToDel.removeListener(this);
                errorLabel.setText("");
            }
        }
    }

    public void onRowClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2)
        {
            searchMusicianViewModel.feed(musiciansTableView.getSelectionModel().getSelectedItem().getUserId());
            viewHandler.viewToPane(Views.MusicianView.name());
        }
        else if (mouseEvent.getClickCount() == 1)
        {
            propertyChangeSupport.firePropertyChange("Musician selection changed",null, musiciansTableView.getSelectionModel().getSelectedItem());
        }
    }

    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removeListener(String name, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }
}
