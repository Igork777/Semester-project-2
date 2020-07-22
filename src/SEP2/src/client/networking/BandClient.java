package client.networking;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public interface BandClient {
    HashMap<String, File> getAllBandsMusicianIsIn(int id);

    ArrayList<Musician> getMusiciansInTheBand(int bandId);

    boolean removeMusicianFromBand(Musician currentMusician, Band localBand);
}
