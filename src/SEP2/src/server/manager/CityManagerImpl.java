package server.manager;

import server.database.CityDBAccess;
import server.database.CityDBAccessImpl;

import java.util.ArrayList;

public class CityManagerImpl implements CityManager{
    private CityDBAccess cityDBAccess;

    public CityManagerImpl() {
        cityDBAccess = new CityDBAccessImpl();
    }

    @Override
    public ArrayList<String> getCities() {
        return cityDBAccess.getCities();
    }

    @Override
    public int getCityByName(String nickName) {
        return cityDBAccess.getCityByName(nickName);
    }

    @Override
    public ArrayList<String> getRegions() {
        return cityDBAccess.getRegions();
    }
}
