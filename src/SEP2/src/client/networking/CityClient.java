package client.networking;

import java.util.ArrayList;

public interface CityClient  {
    ArrayList<String> getCities();
    ArrayList<String> getRegions();
    int getCityByName(String nickName);
}
