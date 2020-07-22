package server.database;

import shared.wrappers.Organizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

public class OrganizerProfileDatabaseImpl implements OrganizerProfileDatabaseAccess
{

	/**
	 * Method which retrieves the organizer information from the database.
	 * @param userId - used id of the organizer
	 * @return Organizer with the data or null
	 */

	@Override public Organizer getOrganizerInfo(int userId)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT u.dateOfBirth AS DOB, u.city, o.fullName, o.bio, o.email, o.telNo FROM organizers o INNER JOIN users u ON u.id = o.organizerUserId WHERE organizerUserId = ?"))
		{
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();


			LocalDate date = LocalDate.parse(resultSet.getString("DOB"));
			String city = resultSet.getString("city");
			String fullName =  resultSet.getString("fullName");
			String bio = resultSet.getString("bio");
			String email = resultSet.getString("email");
			String telNo = resultSet.getString("telNo");
			Organizer organizer = new Organizer(fullName, bio, email, telNo);
			organizer.setDateOfBirth(date);
			organizer.setCity(city);
			return organizer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method which checks whether an user has already registered as an organizer
	 * @param userId - user id of the user the check is done for
	 * @return true if organizer is registered in the system, otherwise false
	 */
	@Override public boolean isOrganizerRegisteredForUser(int userId)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count from organizers where organizerUserId=?"))
		{
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.getInt("count") != 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
