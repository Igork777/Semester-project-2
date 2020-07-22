package server.database;

import shared.wrappers.Band;
import shared.wrappers.Musician;

public interface InviteToBandDatabaseAccess {
    boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician);
}
