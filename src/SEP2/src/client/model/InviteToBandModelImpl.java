package client.model;

import client.networking.InviteToBandClient;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.rmi.RemoteException;

public class InviteToBandModelImpl implements InviteToBandModel {
    private InviteToBandClient inviteToBandClient;

    public InviteToBandModelImpl(InviteToBandClient inviteToBandClient) {
        this.inviteToBandClient = inviteToBandClient;
    }

    @Override
    public boolean inviteToBand(Musician musicianSelected, Band bandToAddMusician) {
        try {
            return inviteToBandClient.inviteMusicianToBand(musicianSelected, bandToAddMusician);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
