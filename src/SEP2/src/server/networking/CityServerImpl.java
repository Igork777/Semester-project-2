package server.networking;

import server.manager.CityManager;
import shared.networking.CityServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CityServerImpl implements CityServer {
    private CityManager cityManager;

    public CityServerImpl(CityManager cityManager, Registry registry)
    {
        this.cityManager = cityManager;
        try {
            UnicastRemoteObject.exportObject(this, 0);
            registry.bind("CityServer", this);
            System.out.println(" City Server started");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getCities() throws RemoteException {
        return cityManager.getCities();
    }

    @Override
    public int getCityByName(String nickName) {
        return cityManager.getCityByName(nickName);
    }

    @Override
    public ArrayList<String> getRegions() {
        return cityManager.getRegions();
    }
}
