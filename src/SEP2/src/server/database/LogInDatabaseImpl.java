package server.database;

import shared.wrappers.User;

import java.sql.*;

/**
 * Implementation of the access to the table users in database BondInBand
 */
public class LogInDatabaseImpl implements LogInDatabaseAccess {


    /**
     * This method checks if inputted nickname and password are correct. Here, I use PreparedStatement (I mentioned above what does it do)
     * in order to check passed parameters with the information from databases.
     * In the result set I get the information from the query. In our case I will have an integer returned, and as the parameter
     * getInt() method will have name of the column, which I want to return.
     * If there is no matching - 0 will be returned, otherwise - 1.
     * Also, all the resources has to be closed in the end
     *
     * @return false is returned if no rows were affected during the query, otherwise true is returned
     */
    @Override
    public boolean login(User user) {

        try (Connection connection = DataBaseAccess.getInstance().getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) as count FROM users WHERE nickname = ? AND password = ?")) {
            preparedStatement.setString(1, user.getLoginName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet =  preparedStatement.executeQuery();
            boolean result = resultSet.getInt("count") != 0;
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while executing query.");
        }
    }
}
