package client.model;

import shared.wrappers.Band;
import shared.wrappers.Musician;

public interface InviteToBandModel {
    boolean inviteToBand (Musician musicianSelected, Band bandToAddMusician);
}
