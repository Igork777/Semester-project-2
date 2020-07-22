package shared.networking;

import shared.wrappers.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RegisterServer extends Remote
{
  String register(User user) throws RemoteException;
}
