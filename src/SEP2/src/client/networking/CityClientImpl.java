package client.networking;

import shared.networking.CityServer;
import shared.networking.CreateBandServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class CityClientImpl implements CityClient {
    private CityServer cityServer;

    public CityClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.cityServer = (CityServer) registry.lookup("CityServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getCities() {
        try {
            return cityServer.getCities();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public ArrayList<String> getRegions() {
        try {
            return cityServer.getRegions();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public int getCityByName(String nickName) {
        try {
            return cityServer.getCityByName(nickName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
