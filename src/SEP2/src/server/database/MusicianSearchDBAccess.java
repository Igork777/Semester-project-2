package server.database;


import shared.wrappers.Musician;

import java.util.ArrayList;
import java.util.Map;

public interface MusicianSearchDBAccess {
    ArrayList<Musician> searchByAll();
    ArrayList<Musician> searchByNickname(String nickname);
    ArrayList<Musician> searchByRegion(String region);
    ArrayList<Musician> searchByNicknameAndRegion(String nickname, String region);
    ArrayList<Musician> searchByInstrumentAndExpertise( Map<Integer, Float> instrumentAndExpertise);
    ArrayList<Musician> searchByNicknameInstrumentAndExpertise(String nickname, Map<Integer, Float> instrumentAndExpertise);
    ArrayList<Musician> searchByRegionInstrumentAndExpertise(String region, Map<Integer, Float> instrumentAndExpertise);
    ArrayList<Musician> searchByNicknameRegionInstrumentAndExpertise(String nickname, String region, Map<Integer, Float> instrumentAndExpertise);
}
