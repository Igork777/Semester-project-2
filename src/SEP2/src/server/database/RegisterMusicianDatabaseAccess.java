package server.database;

import shared.wrappers.Musician;

public interface RegisterMusicianDatabaseAccess
{
    /**
     * Attempts to add a musician profile to databse
     * @param musician musician profile to register
     * @return Outcome of the attempt. Can be: Musician registered or Musician registration failed
     */
    boolean registerMusician(Musician musician);
    /**
     * Retrieves information about user whether it already has a musician profile
     * @param musician user to check for musician profile
     * @return true if the profile already exists false if not
     */
    boolean isMusicianRegisteredForUser(Musician musician);

    boolean addInstrumentsToMusician(Musician musician);
}
