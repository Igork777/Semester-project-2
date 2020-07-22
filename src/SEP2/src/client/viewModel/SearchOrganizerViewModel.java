package client.viewModel;

import client.model.CityModel;
import client.model.SearchOrganizerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.wrappers.Organizer;


public class SearchOrganizerViewModel {
    private SearchOrganizerModel searchOrganizerModel;
    private CityModel cityModel;

    private StringProperty fullName;
    private StringProperty region;
    private ObservableList<Organizer> searchResultOrganizers;
    private ObservableList<String> regions;

    public SearchOrganizerViewModel(SearchOrganizerModel searchOrganizerModel, CityModel cityModel) {
        this.cityModel = cityModel;
        this.searchOrganizerModel = searchOrganizerModel;

        fullName = new SimpleStringProperty();
        region = new SimpleStringProperty();
        searchResultOrganizers = FXCollections.observableArrayList();
        regions = FXCollections.observableArrayList();
        regions.addAll(this.cityModel.getRegions());
    }

    public void search() {
        Organizer organizer = new Organizer();
        organizer.setFullName(fullName.getValue());
        organizer.setRegion(region.getValue());

        searchResultOrganizers.addAll(searchOrganizerModel.search(organizer));
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty regionProperty() {
        return region;
    }

    public ObservableList<Organizer> getSearchResultOrganizers() {
        return searchResultOrganizers;
    }

    public ObservableList<String> getRegions() {
        return regions;
    }

    public void organizerProfileOf(int orgId) {
        searchOrganizerModel.organizerProfileOf(orgId);
    }
}
