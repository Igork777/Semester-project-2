package server.database;

import shared.wrappers.Band;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBandDBImpl implements SearchBandDBAccess
{


	/**
	 * Method containing a query which is used when search only the "Name of the band" search condition is specified
	 * @param name name of the band
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> nameQuery(String name)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c WHERE b.cityId = c.cityId AND b.bandName LIKE ?"))
		{
			preparedStatement.setString(1, "%" + name+ "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method containing a query which is used when search only the "Region" search condition is specified
	 * @param region region the band is located in
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> regionQuery(String region)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c, regions r WHERE b.cityId = c.cityId AND c.regionId = r.regionId AND r.regionName = ?"))
		{
			preparedStatement.setString(1, region);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			System.out.println("SIZE OF THE ARRAY: " + arrayOfBands.size());
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method containing a query which is used when search only the "Genre" search condition is specified
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> genreQuery(int genreId)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c WHERE b.cityId = c.cityId AND  b.genreId = ?"))
		{
			preparedStatement.setInt(1, genreId);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method containing a query which is used when search only the "Name of the band" and "Region" search condition are specified
	 * @param name name of the band
	 * @param region region the band is located in
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> nameRegionQuery(String name, String region)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c, regions r WHERE b.cityId = c.cityId AND c.regionId = r.regionId AND b.bandName LIKE ? AND r.regionName = ?"))
		{
			preparedStatement.setString(1, "%" + name+ "%");
			preparedStatement.setString(2, region);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			System.out.println("SIZE OF THE FUCKING ARRAY IS   " + arrayOfBands.size());
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method containing a query which is used when search only the "Name of the band" and "Genre" search condition are specified
	 * @param name name of the band
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> nameGenreQuery(String name, int genreId)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c WHERE b.cityId = c.cityId AND b.bandName LIKE ? AND b.genreId = ?"))
		{
			preparedStatement.setString(1, "%" + name+ "%");
			preparedStatement.setInt(2, genreId);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method containing a query which is used when search only the "Name of the band" and "Genre" search condition are specified
	 * @param region region the band is located in
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> genreRegionQuery(int genreId, String region)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c, regions r WHERE b.cityId = c.cityId AND c.regionId = r.regionId AND b.genreId = ? AND r.regionName = ?"))
		{
			preparedStatement.setInt(1, genreId);
			preparedStatement.setString(2, region);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method containing a query which is used when all ("Name of the band", "Region", "Genre") the search condition are specified
	 * @param region region the band is located in
	 * @param genreId the id of genre relating to the name of the genre in the database
	 * @param name name of the band
	 * @return ArrayList of Band objects which concur to the search condition. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> nameRegionGenreQuery(String name,
			String region, int genreId)
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c, regions r WHERE b.cityId = c.cityId AND c.regionId = r.regionId AND b.genreId = ? AND r.regionName = ? AND b.bandName LIKE ?"))
		{
			preparedStatement.setInt(1, genreId);
			preparedStatement.setString(2, region);
			preparedStatement.setString(3, "%" + name+ "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	/**
	 * Method containing a query which is used when none of the search conditions are specified
	 * @return ArrayList of all the Band objects in the database. These objects contain
	 * the name of the band, the city the band usually plays in and bandId of the band
	 */
	@Override public ArrayList<Band> noSearchConditionQuery()
	{
		try(Connection connection = DataBaseAccess.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT b.bandName , c.cityName, b.bandId FROM bands b, cities c WHERE b.cityId = c.cityId"))
		{
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Band> arrayOfBands = new ArrayList<>();
			while (resultSet.next())
			{
				Band band = new Band();
				band.setNameOfBand(resultSet.getString("bandName"));
				band.setCityName(resultSet.getString("cityName"));
				band.setBandId(resultSet.getInt("bandId"));
				arrayOfBands.add(band);
			}
			return arrayOfBands;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
