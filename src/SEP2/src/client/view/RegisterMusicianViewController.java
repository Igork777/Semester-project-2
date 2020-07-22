package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewModel.RegisterMusicianViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import shared.utils.Views;
import shared.wrappers.Musician;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterMusicianViewController extends ViewController implements PropertyChangeListener {

    private ViewHandler viewHandler;
    private RegisterMusicianViewModel registerMusicianViewModel;
    private ViewModelFactory viewModelFactory;
    private boolean isOpenedForTheFirstTime = true;

    @FXML
    private TextArea bioId;
    @FXML
    private VBox instrumentsPane;
    @FXML
    private TextField nicknameTextField;
    @FXML
    private ComboBox<String> instrumentsSelector;
    @FXML
    private TextField expTextField;
    @FXML
    private Label errorLabel;

    /**
     * init method for the RegisterMusicianViewController
     *
     * @param viewModelFactory parameter, in order to have the connection with the model layer
     * @param viewHandler      parameter, in order to change views
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.registerMusicianViewModel = viewModelFactory.getRegisterMusicianViewModel();
        nicknameTextField.textProperty().bindBidirectional(registerMusicianViewModel.nicknameProperty());
        errorLabel.textProperty().bindBidirectional(registerMusicianViewModel.errorProperty());
        registerMusicianViewModel.bioProperty().bind(bioId.textProperty());

        if (isOpenedForTheFirstTime) {
            instrumentsSelector.getItems().addAll(Musician.getInstrumentsId().values());
            isOpenedForTheFirstTime = false;
        }
        Thread threadStartMusicianView = new Thread(new Runnable() {
            @Override
            public void run() {
                ViewControllerFactory.getViewController(Views.MusicianView.name());
            }
        });
        threadStartMusicianView.start();
    }

    /**
     * Method triggered when the user clicks Done button
     *
     * @return
     */
    @FXML
    public void registerMusician() {
        errorLabel.textProperty().setValue("");
        boolean isRegistrationSuccessful = registerMusicianViewModel.registerMusician(nicknameTextField.getText(), bioId.getText());
        if (isRegistrationSuccessful) {
            viewHandler.viewToPane(Views.MusicianView.name());
            nicknameTextField.clear();
            instrumentsSelector.setValue(null);
            expTextField.clear();
        } else {
            errorLabel.textProperty().setValue("An error occurred. Musician profile was not created");
        }

    }

    /**
     * Used to add an instrument to the current musician
     *
     * @return
     */
    @FXML
    public boolean addInstrument() {
        String instrument = instrumentsSelector.getValue();
        if (instrument == null || instrument.equals("")) {
            errorLabel.setText("Please select an instrument");
            return false;
        }

        if (expTextField.getText().equals("")) {
            errorLabel.setText("Please input your expertise");
            return false;
        }

        String exp = expTextField.getText().replaceAll("[a-zA-Z+\\-=!@#$%&*()_`~?/\\\\|^]+", "");

        int expertise = Math.round(Float.parseFloat(exp));


        if (registerMusicianViewModel.addInstrument(instrument, expertise)) {
            InstrumentViewController instrumentViewController = (InstrumentViewController) ViewControllerFactory.getNewViewController("InstrumentView");
            instrumentViewController.init(viewModelFactory, viewHandler);
            instrumentViewController.addListener(this);
            instrumentViewController.feed(instrument, expertise);
            viewHandler.rootToPane(instrumentViewController.getRoot(), instrumentsPane);
            instrumentsSelector.valueProperty().set(null);
            expTextField.setText("");
            errorLabel.setText("");
            return true;
        }
        return false;
    }

    /**
     * Method triggered when an event happen
     *
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String event = evt.getPropertyName();
        if (event.equals("Delete Instrument") && evt.getNewValue() instanceof InstrumentViewController) {
            InstrumentViewController instrumentToDel = (InstrumentViewController) evt.getNewValue();
            String instrumentName = instrumentToDel.getInstrument();
            if (registerMusicianViewModel.removeInstrument(instrumentName)) {
                instrumentsPane.getChildren().remove(instrumentToDel.getRoot());
                instrumentToDel.removeListener(this);
            }
        }
    }
}
