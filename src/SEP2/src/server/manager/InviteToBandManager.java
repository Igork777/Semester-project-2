package server.manager;

import shared.wrappers.Band;
import shared.wrappers.Musician;

public interface InviteToBandManager {
    boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician);
}
