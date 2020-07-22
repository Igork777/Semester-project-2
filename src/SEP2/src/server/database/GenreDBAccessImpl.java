package server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenreDBAccessImpl implements GenreDBAccess
{

    /**
     * Method which retrieves all the genres for the combobox from the database
     * @return ArrayList of genres
     */

	@Override public ArrayList<String> getGenres()
	{
		try (Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT genre FROM genres order by genre"))
		{

			ArrayList<String> genres = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				genres.add(resultSet.getString("genre"));
			}
			return genres;
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		return null;
	}

    /**
     * Method which retrieves the genre id by the name of the genre from the database
     * @param genreName - name of the genre
     * @return id of the genre
     */

	@Override public int getGenreIdByName(String genreName)
	{
		try (Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("Select id from genres where genre = ?"))
		{
			preparedStatement.setString(1, genreName);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.getInt("id");
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		return 0;
	}
}
