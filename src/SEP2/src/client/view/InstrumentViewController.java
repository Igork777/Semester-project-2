package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InstrumentViewController extends ViewController {

    private ViewModelFactory viewModelFactory;
    private ViewHandler viewHandler;
    private String instrument;

    private PropertyChangeSupport support;

    @FXML
    public Label instrumentLabel;
    @FXML
    public Label expertiseLabel;

    /**
     * Initializer for the InstrumentViewController
     * @param viewModelFactory parameter, in order to have the connection with the model layer
     * @param viewHandler parameter, in order to change views
     */
    @Override
    public void init(ViewModelFactory viewModelFactory, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Adds listener to the current Instrument
     * @param listener property change listener
     */
    public void addListener(PropertyChangeListener listener){
        this.support.addPropertyChangeListener(listener);
    }

    /**
     *  Removes listener to the current Instrument
     * @param listener
     */
    public void removeListener(PropertyChangeListener listener){
        this.support.removePropertyChangeListener(listener);
    }

    /**
     * Feeds the needed info to the view to be displayed
     * @param instrument
     * @param expertise
     */
    public void feed(String instrument, float expertise) {
        this.instrument = instrument;
        instrumentLabel.setText(instrument);
        expertiseLabel.setText(("" + expertise).replaceAll("\\.0+", ""));
    }

    /**
     * getter for the instrument
     * @return
     */
    public String getInstrument(){
        return instrument;
    }

    /**
     * method triggered when the delete button is clicked
     */
    @FXML
    public void deleteInstrument(){
        support.firePropertyChange("Delete Instrument", null, this);
    }
}
