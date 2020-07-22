package shared.networking;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InviteToBandServer extends Remote {
    boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician) throws RemoteException;
}
