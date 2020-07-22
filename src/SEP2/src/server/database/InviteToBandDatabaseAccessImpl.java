package server.database;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InviteToBandDatabaseAccessImpl implements InviteToBandDatabaseAccess {
    @Override
    public boolean inviteMusicianToBand(Musician musicianSelected, Band bandToAddMusician) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM bandMembers WHERE bandId = ? AND musicianId = ?")) {
            preparedStatement.setInt(1, bandToAddMusician.getBandId());
            preparedStatement.setInt(2, musicianSelected.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean alreadyInBand = resultSet.getInt("count") != 0;

            if (alreadyInBand) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query.");
        }

        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bandMembers(bandId, musicianId) values(?, ?)")) {
            preparedStatement.setInt(1, bandToAddMusician.getBandId());
            preparedStatement.setInt(2, musicianSelected.getUserId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
