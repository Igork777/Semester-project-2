package server.database;

import shared.wrappers.Band;
import shared.wrappers.Musician;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BandDBAccessImpl implements BandDBAccess {
    @Override
    public HashMap<String, File> getAllBandsMusicianIsIn(int id) {
        HashMap<String, File> bands = new HashMap<>();
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("Select bandName, bandLogo from bands b join bandMembers bM on b.bandId = bM.bandId WHERE bM.musicianId = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bands.put(resultSet.getString("bandName"), retrievingImage(resultSet));
            }
            return bands;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return bands;
    }

    @Override
    public boolean removeMusicianFromBand(Musician currentMusician, Band localBand) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from bandMembers where bandId = ? and musicianId = ?;")) {
            preparedStatement.setInt(1, localBand.getBandId());
            preparedStatement.setInt(2, currentMusician.getUserId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query.");
        }
    }


    @Override
    public ArrayList<Musician> getMusiciansInTheBand(int bandId)
    {
        ArrayList<Musician> musicians = new ArrayList<>();
        try(Connection connection = DataBaseAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.*, u.city from musicians m join bandMembers bM on m.musicianUserId = bM.musicianId join users u on m.musicianUserId = u.id WHERE bM.bandId = ?"))
        {
            preparedStatement.setInt(1, bandId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Musician musician = new Musician();
                musician.setUserId(resultSet.getInt("musicianUserId"));
                musician.setNickname(resultSet.getString("nickname"));
                musician.setBio(resultSet.getString("bio"));
                musician.setCity(resultSet.getString("city"));
                musicians.add(musician);
            }
            return musicians;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    private File retrievingImage(ResultSet resultSet) throws SQLException {
        try {
            FileOutputStream fos = null;
            File file = new File("icon.jpg");
            fos = new FileOutputStream(file);
            System.out.println("Writing BLOB to file");

            InputStream input = resultSet.getBinaryStream("bandLogo");
            byte[] buffer = new byte[1024];
            if (input != null) {
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
                return file;
            } else {
                return null;
            }
        } catch (IOException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
