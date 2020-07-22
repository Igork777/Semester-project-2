package shared.networking;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface BandServer extends Remote {

    HashMap<String, File> getAllBandsMusicianIsIn(int id) throws RemoteException;

    ArrayList<Musician> getMusiciansInTheBand(int bandId) throws RemoteException;

    boolean removeMusicianFromBand(Musician currentMusician, Band localBand) throws RemoteException;
}
