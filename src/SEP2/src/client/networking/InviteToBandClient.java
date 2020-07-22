package client.networking;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.rmi.RemoteException;

public interface InviteToBandClient {
    boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician) throws RemoteException;
}
