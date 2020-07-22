package server.database;

import shared.wrappers.Organizer;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizerRegistrationDatabaseImpl implements OrganizerRegistrationDatabaseAccess
{
  /**
   * Method which attempts to add organizer to the database
   * @param organizer - organizer to be added to the database
   * @return message "Happy organizing!" if registration was successful, "Unable to register" if
   * the registration was not successful
   */
  @Override public String registerOrganizer(Organizer organizer)
  {
    String message = "Unable to register";
    try(Connection connection = DataBaseAccess.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO organizers(organizerUserId, fullName, bio, email, telno) VALUES (?,?,?,?,?)"))
    {
      preparedStatement.setInt(1, organizer.getUserId());
      preparedStatement.setString(2, organizer.getFullName());
      preparedStatement.setString(3, organizer.getBiography());
      preparedStatement.setString(4, organizer.getEmail());
      preparedStatement.setString(5, organizer.getTelNo());
      if(preparedStatement.executeUpdate() > 0)
      {
        return "Happy organizing!";
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return message;
  }

  /**
   * Method which checks whether an user has already registered as an organizer
   * @param organizer - organized which is checked for existing profile
   * @return true if organizer is registered in the system, otherwise false
   */
  @Override public boolean isOrganizerRegisteredForUser(Organizer organizer)
  {
    try(Connection connection = DataBaseAccess.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count from organizers where organizerUserId=?"))
    {
      preparedStatement.setInt(1, organizer.getUserId());
      ResultSet resultSet = preparedStatement.executeQuery();
      boolean isOrganizerRegistered = resultSet.getInt("count") != 0;
      return isOrganizerRegistered;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
