package client.model;


import shared.wrappers.Musician;

import shared.wrappers.Band;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public interface BandModel {
     HashMap<String, File> getAllBandsMusicianIsIn(int id);
     ArrayList<Musician> getMusiciansInTheBand(int bandId);

     boolean removeMusicianFromBand(Musician currentMusician, Band localBand) throws Exception;
}
