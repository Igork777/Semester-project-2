package shared.networking;

import shared.wrappers.Band;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CreateBandServer extends Remote
{
    String create(Band band) throws RemoteException;

}
