package client.viewModel;

import client.model.LocalStorageImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.wrappers.Instrument;

public class MusicianViewModel
{
    private SimpleStringProperty nickname;
    private SimpleStringProperty dateOfBirth;
    private SimpleStringProperty city;
    private SimpleStringProperty region;
    private SimpleStringProperty bio;
    private LocalStorageImpl localStorage;
    private ObservableList<Instrument> instruments;

    public MusicianViewModel() {
        this.nickname = new SimpleStringProperty();
        this.dateOfBirth = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.region = new SimpleStringProperty();
        bio = new SimpleStringProperty();
        localStorage = LocalStorageImpl.getInstance();
    }

    public boolean feed()
    {

        nickname.setValue(localStorage.getMusician().getNickname());
        bio.setValue(localStorage.getMusician().getBio());
        dateOfBirth.setValue(localStorage.getMusician().getDateOfBirth()+"");
        city.setValue(localStorage.getMusician().getCity());
        region.setValue(localStorage.getMusician().getRegion());
        instruments = FXCollections.observableArrayList();
        instruments.addAll(localStorage.getPlayedInstrumentsList());
        return true;
    }


    public SimpleStringProperty bioProperty() {
        return bio;
    }

    public SimpleStringProperty nickNameProperty() {
        return nickname;
    }



    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }


    public SimpleStringProperty cityProperty() {
        return city;
    }


    public SimpleStringProperty regionProperty() {
        return region;
    }

    public LocalStorageImpl getLocalStorage() {
        return localStorage;
    }

    public ObservableList<Instrument> getInstruments() {
        instruments = FXCollections.observableArrayList();
        instruments.addAll(localStorage.getPlayedInstrumentsList());
        return instruments;
    }
}
