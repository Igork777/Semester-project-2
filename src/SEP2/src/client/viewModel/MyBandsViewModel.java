package client.viewModel;

import client.model.BandModel;
import client.model.LocalStorageImpl;

import java.io.File;
import java.util.HashMap;

public class MyBandsViewModel
{
    private BandModel bandModel;
    private LocalStorageImpl localStorage;
    private int musicianId;
    public MyBandsViewModel(BandModel bandModel)
    {
        this.bandModel = bandModel;
        localStorage = LocalStorageImpl.getInstance();
    }
    public HashMap<String, File> getAllBandsMusicianIsIn()
    {
        musicianId = localStorage.getMusician().getUserId();
        return bandModel.getAllBandsMusicianIsIn(musicianId);
    }

}
