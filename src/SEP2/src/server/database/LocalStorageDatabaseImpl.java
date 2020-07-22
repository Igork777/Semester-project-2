package server.database;

import shared.wrappers.Band;
import shared.wrappers.Instrument;
import shared.wrappers.Musician;
import shared.wrappers.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LocalStorageDatabaseImpl implements LocalStorageDatabaseAccess
{
    /**
     * Method which returns id, nickname, dateOfBirth city and region
     * base on the nickname provided
     * Try with resources is used in order to close Connection and PreparedStatement after success or the failure of the query
     * @param nickname argument, that is used for creation of the query
     * @return Hashmap which the name of the attribute as key and value as the value of Hashmap
     */
    @Override
    public User fetchUserInfo(String nickname)
    {
        User user = new User();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try (Connection connection = DataBaseAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nickname, dateOfBirth, u.city, r.regionName from users u join cities c on u.city = c.cityName join regions r on c.regionId = r.regionId WHERE nickname = ?"))
        {
            preparedStatement.setString( 1, nickname);
            ResultSet resultSet =  preparedStatement.executeQuery();
            user.setUserId(resultSet.getInt("id"));
            user.setLoginName(resultSet.getString("nickname"));
            Date dateOfBirth = df.parse(resultSet.getString("dateOfBirth"));
            user.setDateOfBirth(dateOfBirth);
            user.setCity(resultSet.getString("city"));
            user.setRegion(resultSet.getString("regionName"));
            return user;
        }
        catch (SQLException | ParseException sqlException)
        {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public Musician fetchMusicianInfo(int id) {
        Musician musician = new Musician();
        try (Connection connection = DataBaseAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from musicians m join users u on m.musicianUserId = u.id join cities c on u.city = c.cityName join regions r on c.regionId = r.regionId  WHERE musicianUserId = ?"))
        {
            preparedStatement.setInt(1, id);
           ResultSet resultSet = preparedStatement.executeQuery();
           if(!resultSet.next())
           {
               return null;
           }
           musician.setUserId(id);
           musician.setNickname(resultSet.getString("nickname"));
           musician.setCity(resultSet.getString("city"));
           musician.setRegion(resultSet.getString("regionName"));
           musician.setDateOfBirth(resultSet.getString("dateOfBirth"));
           musician.setBio(resultSet.getString("bio"));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return musician;
    }

    @Override
    public ArrayList<Instrument> fetchPlayedInstruments(int id)
    {
        try (Connection connection = DataBaseAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT i.instrumentName, mi.expertise from musicianInstruments mi join instruments i on mi.instrumentId = i.instrumentId  WHERE  musicianId = ?"))
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Instrument> instruments = new ArrayList<>();
           while(resultSet.next())
           {
               instruments.add(new Instrument(resultSet.getString("instrumentName"), resultSet.getInt("expertise")));
           }
            return instruments;
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }

    @Override
    public Band fetchBandByName(String bandName) {
        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("Select b.bandId, b.bandName, b.foundationDate, c.cityName, g.genre,b.bio, b.email, b.phoneNr, b.bandAdministratorId, c.cityName, g.genre, b.bandAdministratorId, m.nickname, r.regionName from bands b join cities c on b.cityId = c.cityId join genres g on b.genreId = g.id join musicians m on b.bandAdministratorId = m.musicianUserId join regions r on c.regionId = r.regionId WHERE bandName = ?"))
        {
            preparedStatement.setString(1,bandName);
            Band band = new Band();
            ResultSet resultSet = preparedStatement.executeQuery();
            band.setBandId(resultSet.getInt("bandId"));
            band.setNameOfBand(resultSet.getString("bandName"));
            band.setDateStringRepresentation(resultSet.getString("foundationDate"));
            band.setBio(resultSet.getString("bio"));
            band.setEmail(resultSet.getString("email"));
            band.setRegion(resultSet.getString("regionName"));
            band.setTelephone(resultSet.getString("phoneNr"));
            band.setCityName(resultSet.getString("cityName"));
            band.setGenre(resultSet.getString("genre"));
            band.setCityName(resultSet.getString("cityName"));
            band.setBandAdministratorId(resultSet.getInt("bandAdministratorId"));
            band.setBandAdministratorName(resultSet.getString("nickname"));
            return band;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        throw new RuntimeException("Impossible scenario");
    }
}
