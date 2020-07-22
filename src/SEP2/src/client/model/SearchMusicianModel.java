package client.model;

import shared.wrappers.Musician;

import java.util.ArrayList;

public interface SearchMusicianModel {
    ArrayList<Musician> search(String artistName, String region);

    boolean addInstrument(String instrument, String expertise);

    boolean removeInstrument(String instrumentName);
}
