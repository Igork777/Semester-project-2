package server.database;

import shared.wrappers.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementation of the access to the table users in database BondInBand
 */

public class RegisterDatabaseImpl implements RegisterDatabaseAccess {



    /**
     * This method checks whether the login name is already within the database. PreparedStatement is used
     * in order to check passed parameters with the information from the database.
     * In the result set I will get the information from the query - in this case, an integer will be returned
     * and in the getInt() method, the name of the column we want to check is passed as parameter.
     * the getInt() method will return 0 if there is no match and the name is available and 1 if there is a
     * match and the name is unavailable
     * All resources have to be closed at the end.
     *
     * @param loginName login name of the user for his account
     * @return true if name is available for use, false if name is unavailable for use
     */
    @Override
    public boolean loginNameTaken(String loginName) {

            try (Connection connection = DataBaseAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM users WHERE nickname  = ?"))
            {
                preparedStatement.setString(1, loginName);
                ResultSet resultSet = preparedStatement.executeQuery();
                boolean result = resultSet.getInt("count") == 0;
                return result;
            }
        catch (SQLException e)
        {
            throw new RuntimeException("Failed to execute query.");
        }
    }

    /**
     * This method is used to register a user within a database, once it goes through all the validity checks.
     *
     * @return true, if registration is successful, false if registration is not successful
     */

    @Override
    public boolean register(User user)
    {
        try (Connection connection = DataBaseAccess.getInstance().getConnection();PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(nickname, password, dateOfBirth, city) values(?, ?, ?,?)"))
        {

            preparedStatement.setString(1, user.getLoginName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, String.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(4, user.getCity());

            boolean result = preparedStatement.executeUpdate() > 0;
            return result;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
