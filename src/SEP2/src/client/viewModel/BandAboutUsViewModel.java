package client.viewModel;

import client.model.BandModel;
import client.model.CreateBandModel;
import client.model.LocalStorageImpl;
import javafx.beans.property.SimpleStringProperty;

public class BandAboutUsViewModel {
    private SimpleStringProperty email;
    private SimpleStringProperty telephone;
    private SimpleStringProperty genre;
    private SimpleStringProperty city;
    private SimpleStringProperty foundationDate;
    private SimpleStringProperty region;
    private LocalStorageImpl localStorage;

    public BandAboutUsViewModel() {
        email = new SimpleStringProperty();
        telephone = new SimpleStringProperty();
        genre = new SimpleStringProperty();
        city = new SimpleStringProperty();
        region = new SimpleStringProperty();
        foundationDate = new SimpleStringProperty();
        localStorage = LocalStorageImpl.getInstance();
    }

    public String getRegion() {
        return region.get();
    }

    public SimpleStringProperty regionProperty() {
        return region;
    }

    public void feed()
    {
        email.setValue(localStorage.getLocalBand().getEmail());
        telephone.setValue(localStorage.getLocalBand().getTelephone());
        genre.setValue(localStorage.getLocalBand().getGenre());
        city.setValue(localStorage.getLocalBand().getCityName());
        region.setValue(localStorage.getLocalBand().getRegion());
        foundationDate.setValue(localStorage.getLocalBand().getDateStringRepresentation());
    }
    public String getBio()
    {
        return localStorage.getLocalBand().getBio();
    }




    public SimpleStringProperty emailProperty() {
        return email;
    }


    public SimpleStringProperty telephoneProperty() {
        return telephone;
    }


    public SimpleStringProperty genreProperty() {
        return genre;
    }


    public SimpleStringProperty cityProperty() {
        return city;
    }


    public SimpleStringProperty foundationDateProperty() {
        return foundationDate;
    }
}
