package server.manager;

import shared.wrappers.Musician;

public interface RegisterMusicianManager {
    /**
     * register the musician within the system
     * @param musician musician object to register within the system
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    boolean registerMusician(Musician musician);
}
