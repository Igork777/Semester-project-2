package client.model;

import client.networking.MusicianSearchClient;
import shared.wrappers.Musician;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchMusicianModelImpl implements SearchMusicianModel {
    private Musician musician = new Musician();
    private MusicianSearchClient musicianSearchClient;

    public SearchMusicianModelImpl(MusicianSearchClient musicianSearchClient) {
        this.musicianSearchClient = musicianSearchClient;
    }


    @Override
    public ArrayList<Musician> search(String artistName, String region) {
        this.musician.setNickname(artistName);
        this.musician.setRegion(region);
        return musicianSearchClient.searchForMusicians(musician);
    }

    @Override
    public boolean addInstrument(String instrument, String expertise) throws IllegalArgumentException {
        if (expertise.equals("")) {
            throw new IllegalArgumentException("Please input an integer number as expertise");
        }
        float exp = Math.round(Float.parseFloat(expertise));
        return musician.addInstrument(instrument, exp);
    }

    @Override
    public boolean removeInstrument(String instrumentName) {
        return musician.removeInstrument(instrumentName);
    }
}
