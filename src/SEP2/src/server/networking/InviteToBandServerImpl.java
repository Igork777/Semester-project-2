package server.networking;

import server.manager.InviteToBandManager;
import shared.networking.InviteToBandServer;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class InviteToBandServerImpl implements InviteToBandServer {
    private InviteToBandManager inviteToBandManager;

    public InviteToBandServerImpl(InviteToBandManager inviteToBandManager, Registry registry) {
        this.inviteToBandManager = inviteToBandManager;
        try {
            UnicastRemoteObject.exportObject(this,0);
            registry.bind("InviteServer", this);
            System.out.println("Invite Server started");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician) throws RemoteException {
        return inviteToBandManager.inviteMusicianToBand(musicianSelected, bandToAddMusician);
    }
}
