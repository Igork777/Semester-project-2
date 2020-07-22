package server.database;

import shared.wrappers.Band;
import shared.wrappers.Organizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrganizerDBImpl implements SearchOrganizerDBAccess
{

    @Override
    public ArrayList<Organizer> searchOrganizerByFullName(String organizerFullName) {
        try(Connection connection = DataBaseAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.fullName fullName, u.city city, o.organizerUserId userId FROM users u, organizers o WHERE u.id = o.organizerUserId AND o.fullName LIKE ? "))
        {
            preparedStatement.setString(1, "%" + organizerFullName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Organizer> arrayOfOrganizers = new ArrayList<>();
            while (resultSet.next()) {
                Organizer organizer = new Organizer();
                organizer.setFullName(resultSet.getString("fullName"));
                organizer.setUserId(resultSet.getInt("userId"));
                organizer.setCity(resultSet.getString("city"));
                arrayOfOrganizers.add(organizer);
            }
            return arrayOfOrganizers;
        }
        catch (SQLException e)
        {
            System.out.println("Searching for organizer went wrong.");
            return null;
        }
    }

    @Override
    public ArrayList<Organizer> searchOrganizerByRegion(String region) {
        try(Connection connection = DataBaseAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.fullName fullName, u.city city, o.organizerUserId userId FROM users u, organizers o, cities c, regions r WHERE u.id = o.organizerUserId AND u.city = c.cityName AND c.regionId = r.regionId AND r.regionName = ?"))
        {
            preparedStatement.setString(1, region);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Organizer> arrayOfOrganizers = new ArrayList<>();
            while (resultSet.next()) {
                Organizer organizer = new Organizer();
                organizer.setFullName(resultSet.getString("fullName"));
                organizer.setUserId(resultSet.getInt("userId"));
                organizer.setCity(resultSet.getString("city"));
                arrayOfOrganizers.add(organizer);
            }
            return arrayOfOrganizers;
        }
        catch (SQLException e)
        {
            System.out.println("Searching for organizer went wrong.");
            return null;
        }
    }

    @Override
    public ArrayList<Organizer> searchOrganizerByFullNameAndRegion(String organizerFullName, String region) {
        try(Connection connection = DataBaseAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.fullName fullName, u.city city, o.organizerUserId userId FROM users u, organizers o, cities c, regions r WHERE u.id = o.organizerUserId AND u.city = c.cityName AND c.regionId = r.regionId AND r.regionName = ? AND o.fullName LIKE ?"))
        {
            preparedStatement.setString(1, region);
            preparedStatement.setString(2, "%" + organizerFullName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Organizer> arrayOfOrganizers = new ArrayList<>();
            while (resultSet.next()) {
                Organizer organizer = new Organizer();
                organizer.setFullName(resultSet.getString("fullName"));
                organizer.setUserId(resultSet.getInt("userId"));
                organizer.setCity(resultSet.getString("city"));
                arrayOfOrganizers.add(organizer);
            }
            return arrayOfOrganizers;
        }
        catch (SQLException e)
        {
            System.out.println("Searching for organizer went wrong.");
            return null;
        }
    }

    @Override
    public ArrayList<Organizer> searchAllOrganizers() {
        try(Connection connection = DataBaseAccess.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT o.fullName fullName, u.city city, o.organizerUserId userId FROM users u, organizers o WHERE u.id = o.organizerUserId"))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Organizer> arrayOfOrganizers = new ArrayList<>();
            while (resultSet.next()) {
                Organizer organizer = new Organizer();
                organizer.setFullName(resultSet.getString("fullName"));
                organizer.setUserId(resultSet.getInt("userId"));
                organizer.setCity(resultSet.getString("city"));
                arrayOfOrganizers.add(organizer);
            }
            return arrayOfOrganizers;
        }
        catch (SQLException e)
        {
            System.out.println("Searching for organizer went wrong.");
            return null;
        }
    }
}
