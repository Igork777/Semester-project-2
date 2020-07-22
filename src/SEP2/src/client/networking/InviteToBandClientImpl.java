package client.networking;

import shared.networking.InviteToBandServer;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class InviteToBandClientImpl implements InviteToBandClient {
    private InviteToBandServer inviteToBandServer;

    public InviteToBandClientImpl() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            this.inviteToBandServer = (InviteToBandServer) registry.lookup("InviteServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician) throws RemoteException {
        try {
            return inviteToBandServer.inviteMusicianToBand(musicianSelected, bandToAddMusician);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
