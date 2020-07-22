package server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDBAccessImpl implements CityDBAccess {
    @Override
    public ArrayList<String> getCities()
    {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("Select cityName from cities order by cityName"))
        {

            ArrayList<String> cities = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cities.add(resultSet.getString("cityName"));
            }
            return cities;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public int getCityByName(String nickName)
    {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("Select cityId from cities where cityName = ?")) {
            ArrayList<String> cities = new ArrayList<>();
            preparedStatement.setString(1, nickName);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.getInt("cityId");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<String> getRegions() {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("Select regionName from regions")) {
            ArrayList<String> regions = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                regions.add(resultSet.getString("regionName"));
            }
            return regions;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
