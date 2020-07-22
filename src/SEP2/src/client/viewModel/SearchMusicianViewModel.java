package client.viewModel;

import client.model.LocalStorageImpl;
import client.model.SearchMusicianModel;
import shared.wrappers.Musician;

import java.util.List;

public class SearchMusicianViewModel
{

    private SearchMusicianModel searchMusicianModel;
    private LocalStorageImpl localStorage = LocalStorageImpl.getInstance();

    public SearchMusicianViewModel(SearchMusicianModel searchMusicianModel) {
        this.searchMusicianModel = searchMusicianModel;
    }

    public List<Musician> search(String artistName, String region) {
        return searchMusicianModel.search(artistName, region);
    }

    public boolean addInstrument(String instrument, String expertise) throws IllegalArgumentException {
        return searchMusicianModel.addInstrument(instrument, expertise);
    }

    public boolean removeInstrument(String instrumentName) {
        return searchMusicianModel.removeInstrument(instrumentName);
    }

    public void feed(int userId) {
        localStorage.fetchMusicianInfo(userId);
        localStorage.fetchPlayedInstruments(userId);
    }
}
