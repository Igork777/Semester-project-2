package server.database;

import shared.wrappers.Musician;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class RegisterMusicianDatabaseImpl implements RegisterMusicianDatabaseAccess {


    /**
     * Attempts to add a musician profile to database
     *
     * @param musician musician profile to register
     * @return true when musician is inserted in database; false when musician is alrady inserted in database or the query fails
     */
    @Override
    public boolean registerMusician(Musician musician) {
        boolean isInserted = false;
        if (musician.getBio() == null) {
            try (Connection connection = DataBaseAccess.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO musicians (musicianUserId, nickname, bio) VALUES (?,?,?)")) {
                    preparedStatement.setInt(1, musician.getUserId());
                    preparedStatement.setString(2, musician.getNickname());
                    preparedStatement.setString(3,musician.getBio());
                    isInserted = preparedStatement.executeUpdate() > 0;
                    return isInserted;
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        else {
            try (Connection connection = DataBaseAccess.getInstance()
                .getConnection();
                PreparedStatement preparedStatement = connection
                    .prepareStatement(
                        "INSERT INTO musicians (musicianUserId, nickname, bio) VALUES (?, ?, ?)")) {
                preparedStatement.setInt(1, musician.getUserId());
                preparedStatement.setString(2, musician.getNickname());
                preparedStatement.setString(3, musician.getBio());
                isInserted = preparedStatement.executeUpdate() > 0;
                return isInserted;
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return isInserted;
    }

    /**
     * Retrieves information about user whether it already has a musician profile registered in the database
     * Checks whether the userId already is in musicians table's musicianId
     *
     * @param musician musician to check for musician profile
     * @return true if the profile already exists; false if profile does not  exist
     */
    @Override
    public boolean isMusicianRegisteredForUser(Musician musician) {

        try (Connection connection = DataBaseAccess.getInstance()
            .getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM musicians WHERE musicianUserId = ?")) {
                preparedStatement.setInt(1, musician.getUserId());
                ResultSet resultSet = preparedStatement.executeQuery();
                boolean isMusicianRegistered = resultSet.getInt("count") != 0;
                return isMusicianRegistered;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;

    }

    /**
     * Creates a query inserting all the instruments the musician plays as wel as their expertise with them
     * <p>Firstly, creates an arraylist of type where all the instruments and expertise are extracted from hashmap<br>
     * in query format</p>
     * <p>Then joins all the elements in a single query to be executed</p>
     *
     * @param musician whom instruments are to be added
     * @return true if all instruments were added to a musicianInstruments table;
     * false if the instruments were not added to a musicianInstruments table
     */
    @Override
    public boolean addInstrumentsToMusician(Musician musician) {
        Map<Integer, Float> instruments = musician.getInstrumentsPlayed();
        ArrayList<String> instrumentQueries = new ArrayList<>();
        for (Map.Entry instrumentSet : instruments.entrySet()) {
            Integer instrument = (Integer) instrumentSet.getKey();
            Integer expertise = Math.round((float) instrumentSet.getValue());
            instrumentQueries.add(
                "(" + musician.getUserId() + ", " + instrument + ", "
                    + expertise + ")");
        }
        try (Connection connection = DataBaseAccess.getInstance()
            .getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO musicianInstruments (musicianId, instrumentId, expertise) VALUES "
                    + String.join(", ", instrumentQueries) + ";")) {
            boolean added = preparedStatement.executeUpdate() > 0;
            return added;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
