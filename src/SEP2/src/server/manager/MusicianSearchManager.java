package server.manager;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.util.ArrayList;

public interface MusicianSearchManager {
    ArrayList<Musician> searchForMusicians(Musician musician);
}
