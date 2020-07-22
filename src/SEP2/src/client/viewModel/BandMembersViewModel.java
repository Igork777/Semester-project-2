package client.viewModel;

import client.model.BandModel;
import client.model.LocalStorageImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.wrappers.Musician;

import java.util.ArrayList;

public class BandMembersViewModel
{
    private LocalStorageImpl localStorage = LocalStorageImpl.getInstance();
    private BandModel bandModel;
    private StringProperty removalOfTheMusician;
    public BandMembersViewModel(BandModel bandModel)
    {
        this.bandModel = bandModel;
        removalOfTheMusician = new SimpleStringProperty();
    }

    public boolean checkIfMusicianIsBandAdministrator()
    {
        return localStorage.getLocalBand().getBandAdministratorId() == localStorage.getMusician().getUserId();
    }
    public StringProperty removalOfTheMusicianProperty() {
        return removalOfTheMusician;
    }

    public ArrayList<Musician> getMusiciansInTheBand()
    {
        int bandId = localStorage.getLocalBand().getBandId();
        return bandModel.getMusiciansInTheBand(bandId);
    }
    public void removeMusicianFromBand(Musician musician)
    {
        try {
            bandModel.removeMusicianFromBand(musician, localStorage.getLocalBand());
            removalOfTheMusician.setValue("Musician " + musician.getNickname() + " is deleted successfully");
        } catch (Exception e) {
           removalOfTheMusician.setValue(e.getMessage());
        }
    }
}
