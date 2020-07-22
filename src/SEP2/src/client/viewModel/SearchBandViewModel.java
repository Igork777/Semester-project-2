package client.viewModel;

import client.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.wrappers.Band;
import java.util.ArrayList;
import java.util.List;

public class SearchBandViewModel {

    private SimpleStringProperty bandName;
    private SimpleStringProperty genre;
    private SimpleStringProperty region;
    private GenreModel genreModel;
    private SearchBandModel searchBandModel;
    private ObservableList<Band> resultsObservableList;
    private ObservableList<String> regionObservableList;

    public SearchBandViewModel(SearchBandModel searchBandModel, GenreModel genreModel, CityModel cityModel) {
        this.bandName = new SimpleStringProperty();
        this.genre = new SimpleStringProperty();
        this.region = new SimpleStringProperty();
        this.genreModel = genreModel;
        this.searchBandModel = searchBandModel;
        resultsObservableList = FXCollections.observableArrayList();
        regionObservableList = FXCollections.observableArrayList();
        regionObservableList.addAll(cityModel.getRegions());
    }


    public void search() {
        Band band = new Band();
        band.setNameOfBand(bandName.get());
        band.setRegion(region.get());
        //check because of SQLException
        if(genre.get() != null)
        {
            band.setGenreId(genreModel.getGenreIdByName(genre.get()));
        }
        System.out.println(band.getNameOfTheBand());
        System.out.println(band.getRegion());
        System.out.println(band.getGenreId());
        this.resultsObservableList.addAll(searchBandModel.search(band));
    }

    public SimpleStringProperty bandNameProperty()
    {
        return bandName;
    }

    public SimpleStringProperty genreProperty()
    {
        return genre;
    }

    public SimpleStringProperty regionProperty()
    {
        return region;
    }

    public ArrayList<String> genresForComboBox()
    {
        return genreModel.getGenres();
    }

    public ObservableList<Band> listOfResults()
    {
        return resultsObservableList;
    }

    public ObservableList<String> getRegionsForComboBox()
    {
        return regionObservableList;
    }
}
