package client.viewModel;

import client.model.BandModel;
import client.model.CreateBandModel;
import client.model.LocalStorageImpl;
import javafx.beans.property.SimpleStringProperty;
import shared.wrappers.Band;
import shared.wrappers.User;


public class BandProfileViewModel {
    private SimpleStringProperty nameOfTheBandProperty;
    private LocalStorageImpl localStorage;
    private BandModel bandModel;

    public BandProfileViewModel(BandModel bandModel) {
        localStorage = LocalStorageImpl.getInstance();
        nameOfTheBandProperty = new SimpleStringProperty();
        this.bandModel = bandModel;
    }

    public void feed() {
        nameOfTheBandProperty.setValue(localStorage.getLocalBand().getNameOfTheBand());
    }

    public Band getCurrentBand() {
        return localStorage.getLocalBand();
    }

    public SimpleStringProperty nameOfTheBandPropertyProperty() {
        return nameOfTheBandProperty;
    }

    public User getViewer() {
        return localStorage.getCurrentUser();
    }


    public boolean leaveBand() throws Exception {
        return bandModel.removeMusicianFromBand(localStorage.getMusician(), localStorage.getLocalBand());
    }
}
