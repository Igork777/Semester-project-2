package server.manager;

import server.database.InviteToBandDatabaseAccess;
import server.database.InviteToBandDatabaseAccessImpl;
import shared.wrappers.Band;
import shared.wrappers.Musician;

public class InviteToBandManagerImpl implements InviteToBandManager {
    private InviteToBandDatabaseAccess inviteToBandDatabaseAccess;

    public InviteToBandManagerImpl() {
        this.inviteToBandDatabaseAccess = new InviteToBandDatabaseAccessImpl();
    }

    @Override
    public boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician) {
        return inviteToBandDatabaseAccess.inviteMusicianToBand(musicianSelected, bandToAddMusician);
    }
}
