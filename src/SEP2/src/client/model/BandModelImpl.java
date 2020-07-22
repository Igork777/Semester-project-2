package client.model;

import client.networking.BandClient;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class BandModelImpl implements BandModel {
    private BandClient bandClient;

    public BandModelImpl(BandClient bandClient)
    {
        this.bandClient = bandClient;
    }

    @Override
    public HashMap<String, File> getAllBandsMusicianIsIn(int id) {
        return bandClient.getAllBandsMusicianIsIn(id);
    }

    @Override
    public ArrayList<Musician> getMusiciansInTheBand(int bandId) {
        return bandClient.getMusiciansInTheBand(bandId);
    }

    @Override
    public boolean removeMusicianFromBand(Musician currentMusician, Band localBand) throws Exception {
        if(localBand.getBandAdministratorId() != currentMusician.getUserId()){
            return bandClient.removeMusicianFromBand(currentMusician, localBand);
        }else{
            throw new Exception("You can't remove the Administrator from the band!");
        }
    }
}
