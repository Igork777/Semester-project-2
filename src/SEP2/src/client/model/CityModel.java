package client.model;

import java.util.ArrayList;

public interface CityModel
{
    ArrayList<String> getCities();
    ArrayList<String> getRegions();
    int getCityIdByName(String nickName);
}
