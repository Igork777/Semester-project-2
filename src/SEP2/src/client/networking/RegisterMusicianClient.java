package client.networking;

import shared.wrappers.Musician;

public interface RegisterMusicianClient
{
    /**
     * register the musician within the system
     * @param musician musician object to register within the system
     * @return true = success, false = fail
     * @throws IllegalArgumentException
     */
    boolean register(Musician musician);
}
