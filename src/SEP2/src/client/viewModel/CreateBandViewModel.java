package client.viewModel;

import client.model.CityModel;
import client.model.CreateBandModel;
import client.model.GenreModel;
import client.model.LocalStorageImpl;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.wrappers.Band;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreateBandViewModel {
    private StringProperty nameOfTheBandProperty;
    private StringProperty bio;
    private StringProperty bioError;
    private StringProperty emailProperty;
    private StringProperty telephoneProperty;
    private StringProperty genreProperty;
    private StringProperty nameErrorProperty;
    private StringProperty telephoneErrorProperty;
    private StringProperty foundationDateErrorProperty;
    private StringProperty genreErrorProperty;
    private StringProperty cityErrorProperty;
    private StringProperty emailErrorProperty;
    private StringProperty cityProperty;
    private StringProperty errorCreatingBand;
    private ObjectProperty<LocalDate> foundationDate;
    private CreateBandModel createBandModel;
    private CityModel cityModel;
    private GenreModel genreModel;
    private LocalStorageImpl localStorage = LocalStorageImpl.getInstance();

    public CreateBandViewModel(CreateBandModel createBandModel, CityModel cityModel, GenreModel genreModel) {
        foundationDateErrorProperty = new SimpleStringProperty();
        genreErrorProperty = new SimpleStringProperty();
        cityErrorProperty = new SimpleStringProperty();
        nameOfTheBandProperty = new SimpleStringProperty();
        emailProperty = new SimpleStringProperty();
        telephoneProperty = new SimpleStringProperty();
        genreProperty = new SimpleStringProperty();
        foundationDate = new SimpleObjectProperty<>();
        nameErrorProperty = new SimpleStringProperty();
        telephoneErrorProperty = new SimpleStringProperty();
        emailErrorProperty = new SimpleStringProperty();
        cityProperty = new SimpleStringProperty();
        errorCreatingBand = new SimpleStringProperty();
        bio = new SimpleStringProperty();
        bioError = new SimpleStringProperty();


        this.createBandModel = createBandModel;
        this.cityModel = cityModel;
        this.genreModel = genreModel;
    }

    public void create() {
        nameErrorProperty.setValue("");
        telephoneErrorProperty.setValue("");
        emailErrorProperty.setValue("");
        int bandAdministratorId = localStorage.getMusician().getUserId();
        Band band = new Band(nameOfTheBandProperty.getValue(), emailProperty.getValue(), telephoneProperty.getValue(), genreModel.getGenreIdByName(genreProperty.getValue()), cityModel.getCityIdByName(cityProperty.getValue()), foundationDate.getValue(), bio.getValue(), bandAdministratorId);
        String string = createBandModel.create(band);


        if (string.startsWith("Name")) {
            nameErrorProperty.setValue(string);
            return;
        }
        if (string.endsWith("date")) {
            foundationDateErrorProperty.setValue(string);
            return;
        }
        if (string.endsWith("telephone number")) {
            errorCreatingBand.setValue(string);
            return;
        }
        if (string.startsWith("Telephone")) {
            telephoneErrorProperty.setValue(string);
            return;
        }
        if (string.startsWith("Email")) {
            emailErrorProperty.setValue(string);
            return;
        }
        if (string.endsWith("genre")) {
            genreErrorProperty.setValue(string);
            return;
        }
        if (string.endsWith("city")) {
            cityErrorProperty.setValue(string);
            return;
        }
        if (string.startsWith("Biography")) {
            bioError.setValue(string);
            return;
        }
        if (string.startsWith("Band")) {
            if (string.equals("Band created successfully!")) {
                localStorage.setLocalBandByName(nameOfTheBandProperty.getValue());
            }
            errorCreatingBand.setValue(string);
        }

    }

    public StringProperty getFoundationDateErrorProperty() {
        return foundationDateErrorProperty;
    }

    public StringProperty genreErrorProperty() {
        return genreErrorProperty;
    }

    public StringProperty cityErrorProperty() {
        return cityErrorProperty;
    }

    public StringProperty errorCreatingBandProperty() {
        return errorCreatingBand;
    }

    public StringProperty bioProperty() {
        return bio;
    }

    public StringProperty bioError() {
        return bioError;
    }

    public ArrayList<String> getCities() {
        return cityModel.getCities();
    }

    public ArrayList<String> getGenres() {
        return genreModel.getGenres();
    }


    public StringProperty cityProperty() {
        return cityProperty;
    }

    public StringProperty nameErrorProperty() {
        return nameErrorProperty;
    }


    public StringProperty telephoneErrorProperty() {
        return telephoneErrorProperty;
    }


    public StringProperty emailErrorProperty() {
        return emailErrorProperty;
    }

    public StringProperty genreProperty() {
        return genreProperty;
    }


    public ObjectProperty<LocalDate> foundationDateProperty() {
        return foundationDate;
    }


    public StringProperty nameOfTheBandProperty() {
        return nameOfTheBandProperty;
    }


    public StringProperty emailProperty() {
        return emailProperty;
    }


    public StringProperty telephoneProperty() {
        return telephoneProperty;
    }


}
