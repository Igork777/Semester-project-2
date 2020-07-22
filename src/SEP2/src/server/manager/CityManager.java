package server.manager;

import java.util.ArrayList;

public interface CityManager
{
    ArrayList<String> getCities();

    int getCityByName(String nickName);

    ArrayList<String> getRegions();
}
