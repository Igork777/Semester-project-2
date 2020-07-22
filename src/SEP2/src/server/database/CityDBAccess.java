package server.database;

import java.util.ArrayList;

public interface CityDBAccess
{
    ArrayList <String> getCities();

    int getCityByName(String nickName);

    ArrayList<String> getRegions();
}
