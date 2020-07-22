package server.manager;

import server.database.BandDBAccess;
import server.database.BandDBAccessImpl;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class BandManagerImpl implements BandManager {
    private BandDBAccess bandDBAccess;

    public BandManagerImpl() {
        bandDBAccess = new BandDBAccessImpl();
    }


    @Override
    public HashMap<String, File> getAllBandsMusicianIsIn(int id) {
        return bandDBAccess.getAllBandsMusicianIsIn(id);
    }

    @Override
    public ArrayList<Musician> getMusiciansInTheBand(int bandId) {
        return bandDBAccess.getMusiciansInTheBand(bandId);
    }

    @Override
    public boolean removeMusicianFromBand(Musician currentMusician, Band localBand) {
        return bandDBAccess.removeMusicianFromBand(currentMusician, localBand);
    }
}
