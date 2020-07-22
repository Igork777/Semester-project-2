package server.networking;

import server.manager.BandManager;
import shared.networking.BandServer;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class BandServerImpl implements BandServer {
    private BandManager bandManager;

    public BandServerImpl(BandManager bandManager, Registry registry) {
        this.bandManager = bandManager;
        try {
            UnicastRemoteObject.exportObject(this,0);
            registry.bind("BandServer", this);
            System.out.println(" Band Server started");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public HashMap<String, File> getAllBandsMusicianIsIn(int id) {
        return bandManager.getAllBandsMusicianIsIn(id);
    }

    @Override
    public boolean removeMusicianFromBand(Musician currentMusician, Band localBand)  {
        return bandManager.removeMusicianFromBand(currentMusician, localBand);
    }

    @Override
    public ArrayList<Musician> getMusiciansInTheBand(int bandId) {
        return bandManager.getMusiciansInTheBand(bandId);
    }
}
