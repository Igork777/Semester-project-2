package server.database;

import shared.wrappers.Band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateBandDBAccessImpl implements CreateBandDBAccess {
    @Override
    public String create(Band band) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM bands WHERE bandName = ?")) {
            preparedStatement.setString(1, band.getNameOfTheBand());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean available = resultSet.getInt("count") == 0;
            if (!available) {
                return "Band name already taken!";
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query.");
        }
        // TODO cityId
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bands(bandName, foundationDate, email, phoneNr, cityId, genreId, bio, bandAdministratorId) values(?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, band.getNameOfTheBand());
            preparedStatement.setString(2, "" + band.getDate());
            preparedStatement.setString(3, band.getEmail());
            preparedStatement.setString(4, band.getTelephone());
            preparedStatement.setInt(5, band.getCityId());
            preparedStatement.setInt(6, band.getGenreId());
            preparedStatement.setString(7, band.getBio());
            preparedStatement.setInt(8, band.getBandAdministratorId());
            boolean result = preparedStatement.executeUpdate() > 0;
            if(result)
            {

                 PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT bandId from bands where bandName = ?");
                 preparedStatement2.setString(1,band.getNameOfTheBand());
                 ResultSet resultSet = preparedStatement2.executeQuery();
                 int idOfTheBand = resultSet.getInt("bandId");
                 PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO bandMembers(bandId, musicianId) values (?,?)");
                 preparedStatement3.setInt(1, idOfTheBand);
                 preparedStatement3.setInt(2, band.getBandAdministratorId());
                 preparedStatement3.executeUpdate();
                 preparedStatement2.close();
                 preparedStatement3.close();
                return "Band created successfully!";
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return "Band creation gone wrong.";
    }

}
