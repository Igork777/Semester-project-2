package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CityServer extends Remote
{
    ArrayList<String> getCities() throws RemoteException;

    int getCityByName(String nickName) throws RemoteException;

    ArrayList<String> getRegions() throws RemoteException;
}
