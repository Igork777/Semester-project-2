package client.viewModel;

import client.model.LocalStorageImpl;

public class BandItemViewModel {
    private LocalStorageImpl localStorage;

    public BandItemViewModel() {
        localStorage = LocalStorageImpl.getInstance();
    }
    public void feed(String nickName)
    {
        localStorage.setLocalBandByName(nickName);
    }
}
