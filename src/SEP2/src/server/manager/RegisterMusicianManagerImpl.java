package server.manager;

import server.database.RegisterMusicianDatabaseAccess;
import server.database.RegisterMusicianDatabaseImpl;
import shared.wrappers.Musician;

public class RegisterMusicianManagerImpl implements RegisterMusicianManager
{
    private RegisterMusicianDatabaseAccess musicianRegistrationDB;

    /**
     * Constructor creating a database access point for musician registration
     */
    public RegisterMusicianManagerImpl() {
        musicianRegistrationDB = new RegisterMusicianDatabaseImpl();
    }

    /**
     * Attempt to register a musician profile if the profile does not exist already
     * @param musician musician profile to register
     * @return outcome of the attempt
     */
    @Override
    public boolean registerMusician(Musician musician) {
        if (musicianRegistrationDB.isMusicianRegisteredForUser(musician))
            return false;
        else {
            boolean registered = musicianRegistrationDB.registerMusician(musician);
            musicianRegistrationDB.addInstrumentsToMusician(musician);
            return registered;
        }
    }
}
