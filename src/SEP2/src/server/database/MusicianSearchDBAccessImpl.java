package server.database;


import shared.wrappers.Musician;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class MusicianSearchDBAccessImpl implements MusicianSearchDBAccess {
    private StringBuilder stringBuilder;
    private int size, iterator;


    private ArrayList<Musician> getMusicians(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Musician> searchedMusicians = new ArrayList<>();
        while (resultSet.next()) {
            Musician musician = new Musician();
            musician.setUserId(resultSet.getInt("musicianUserId"));
            musician.setNickname(resultSet.getString("nickname"));
            musician.setCity(resultSet.getString("city"));
            searchedMusicians.add(musician);
        }
        return searchedMusicians;
    }

    private ArrayList<Musician> getMusicians(String string) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(string);
            ArrayList<Musician> searchedMusicians = new ArrayList<>();
            while (resultSet.next()) {
                Musician musician = new Musician();
                musician.setUserId(resultSet.getInt("musicianUserId"));
                musician.setNickname(resultSet.getString("nickname"));
                musician.setCity(resultSet.getString("city"));
                searchedMusicians.add(musician);
            }
            return searchedMusicians;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public ArrayList<Musician> searchByAll() {
        try (Connection connection = DataBaseAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id ")) {
            return getMusicians(preparedStatement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Search by all is failed");
    }


    @Override
    public ArrayList<Musician> searchByNickname(String nickname) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id WHERE m.nickname like ? ")) {
            preparedStatement.setString(1, "%" + nickname + "%");
            return getMusicians(preparedStatement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Search by nickname is failed");
    }

    @Override
    public ArrayList<Musician> searchByRegion(String region) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id JOIN cities c on u.city = c.cityName JOIN regions r on c.regionId = r.regionId WHERE r.regionName = ?")) {
            preparedStatement.setString(1, region);
            return getMusicians(preparedStatement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Search by region is failed");
    }


    @Override
    public ArrayList<Musician> searchByNicknameAndRegion(String nickname, String region) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id JOIN cities c on u.city = c.cityName JOIN regions r on c.regionId = r.regionId WHERE m.nickname LIKE ? AND r.regionName = ? ")) {
            preparedStatement.setString(1, "%" + nickname + "%");
            preparedStatement.setString(2, region);
            return getMusicians(preparedStatement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Search by nickname and regions is failed");
    }

    private void iteratorThroughInstruments(Map<Integer, Float> instrumentAndExpertise) {
        size = instrumentAndExpertise.size();
        iterator = 0;
        for (Map.Entry<Integer, Float> instAndExp : instrumentAndExpertise.entrySet()) {
            stringBuilder.append("(SELECT count(*) as count FROM musicians m JOIN users on m.musicianUserId = u.id JOIN musicianInstruments mI on m.musicianUserId = mI.musicianId JOIN instruments i on mI.instrumentId = i.instrumentId WHERE ").append(" mI.instrumentId = ").append(instAndExp.getKey()).append(" AND mI.expertise >= ").append(instAndExp.getValue()).append(") > 0 ");
            if (iterator < size - 1) {
                stringBuilder.append(" AND ");
            } else {
                stringBuilder.append(";");
            }
            iterator++;
        }
    }

    @Override
    public ArrayList<Musician> searchByInstrumentAndExpertise(Map<Integer, Float> instrumentAndExpertise) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id JOIN musicianInstruments mI on m.musicianUserId = mI.musicianId JOIN instruments i on mI.instrumentId = i.instrumentId WHERE ");
        iteratorThroughInstruments(instrumentAndExpertise);
        return getMusicians(stringBuilder.toString());
    }


    @Override
    public ArrayList<Musician> searchByNicknameInstrumentAndExpertise(String nickname, Map<Integer, Float> instrumentAndExpertise) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id  JOIN musicianInstruments mI on m.musicianUserId = mI.musicianId JOIN instruments i on mI.instrumentId = i.instrumentId WHERE m.nickname LIKE '%").append(nickname).append("%' AND ");
        iteratorThroughInstruments(instrumentAndExpertise);
        return getMusicians(stringBuilder.toString());
    }

    @Override
    public ArrayList<Musician> searchByRegionInstrumentAndExpertise(String region, Map<Integer, Float> instrumentAndExpertise) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id JOIN cities c on u.city = c.cityName JOIN regions r on c.regionId = r.regionId JOIN musicianInstruments mI on m.musicianUserId = mI.musicianId JOIN instruments i on mI.instrumentId = i.instrumentId WHERE r.regionName = '").append(region).append("' AND ");
        iteratorThroughInstruments(instrumentAndExpertise);
        return getMusicians(stringBuilder.toString());
    }

    @Override
    public ArrayList<Musician> searchByNicknameRegionInstrumentAndExpertise(String nickname, String region, Map<Integer, Float> instrumentAndExpertise) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT DISTINCT m.musicianUserId, m.nickname, u.city FROM musicians m JOIN users u on m.musicianUserId = u.id JOIN cities c on u.city = c.cityName JOIN regions r on c.regionId = r.regionId JOIN musicianInstruments mI on m.musicianUserId = mI.musicianId JOIN instruments i on mI.instrumentId = i.instrumentId  WHERE m.nickname LIKE '%").append(nickname).append("%' AND r.regionName = '").append(region).append("' AND ");
        iteratorThroughInstruments(instrumentAndExpertise);
        return getMusicians(stringBuilder.toString());
    }
}
