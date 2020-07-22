package server.manager;

import server.database.MusicianSearchDBAccess;
import server.database.MusicianSearchDBAccessImpl;
import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.util.ArrayList;
import java.util.Map;

public class MusicianSearchManagerImpl implements MusicianSearchManager {
    private MusicianSearchDBAccess musicianSearchDBAccess;

    public MusicianSearchManagerImpl() {
        musicianSearchDBAccess = new MusicianSearchDBAccessImpl();
    }

    @Override
    public ArrayList<Musician> searchForMusicians(Musician musician) {
        String nickname = musician.getNickname();
        String region = musician.getRegion();
        Map<Integer, Float> instrumentAndExpertise = musician.getInstrumentsPlayed();
        if (nickname.equals("") && region == null && instrumentAndExpertise.isEmpty()) {
            return musicianSearchDBAccess.searchByAll();
        }
        if (!nickname.equals("") && region == null && instrumentAndExpertise.isEmpty()) {
            return musicianSearchDBAccess.searchByNickname(nickname);
        }
        if (nickname.equals("") && region != null && instrumentAndExpertise.isEmpty()) {
            return musicianSearchDBAccess.searchByRegion(region);
        }
        if (!nickname.equals("") && region != null && instrumentAndExpertise.isEmpty()) {
            return musicianSearchDBAccess.searchByNicknameAndRegion(nickname, region);
        }
        if (nickname.equals("") && region == null) {
            return musicianSearchDBAccess.searchByInstrumentAndExpertise(instrumentAndExpertise);
        }
        if (!nickname.equals("") && region == null) {
            return musicianSearchDBAccess.searchByNicknameInstrumentAndExpertise(nickname, instrumentAndExpertise);
        }
        if(nickname.equals(""))
        {
            return musicianSearchDBAccess.searchByRegionInstrumentAndExpertise(region,instrumentAndExpertise);
        }
            return musicianSearchDBAccess.searchByNicknameRegionInstrumentAndExpertise(nickname,region,instrumentAndExpertise);

    }
}
