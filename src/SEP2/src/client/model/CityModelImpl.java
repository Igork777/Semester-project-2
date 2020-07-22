package client.model;

import client.networking.CityClient;

import java.util.ArrayList;

public class CityModelImpl implements CityModel
{
    private CityClient cityClient;
    public CityModelImpl(CityClient cityClient)
    {
        this.cityClient = cityClient;
    }

    @Override
    public ArrayList<String> getCities() {
        return cityClient.getCities();
    }

    @Override
    public ArrayList<String> getRegions() {
        return cityClient.getRegions();
    }

    @Override
    public int getCityIdByName(String nickName) {
        return cityClient.getCityByName(nickName);
    }
}
