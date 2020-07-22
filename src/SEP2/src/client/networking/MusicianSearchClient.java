package client.networking;

import shared.wrappers.Musician;

import java.util.ArrayList;

public interface MusicianSearchClient
{
    ArrayList<Musician> searchForMusicians(Musician musician);
}
