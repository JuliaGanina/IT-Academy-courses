package by.itacademy.ganina.dao.jdbc;

import by.itacademy.ganina.dao.DaoException;
import by.itacademy.ganina.dao.JdbcDaoException;
import by.itacademy.ganina.dao.JdbcDaoException.ClientJdbcDaoException;
import by.itacademy.ganina.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.ganina.model.Client;

import java.sql.*;

public class ClientJdbcDao extends GenericJdbcDao<Client> {

    public ClientJdbcDao(final JdbcDataSourceProperties properties, final String tableName) throws DaoException {
        super(properties, "client");
    }

    @Override
    protected PreparedStatement getPreparedStatementToCreate(final Connection connection, final Client client) throws SQLException {
        try {
            final String query = "INSERT INTO client(first_name, last_name) VALUES (?,?)";

            final PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());

            return preparedStatement;
        } catch (final Exception ex) {
            throw new SQLException("Failed to create sqlQuery into client table", ex);
        }
    }

    @Override
    protected PreparedStatement getPreparedStatementToUpdate(final Connection connection,final Client client) throws SQLException {
        try {
            final String query = "UPDATE client SET 'first_name'=?, 'last_name'=? WHERE id=?";

            final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3,client.getId());

            return preparedStatement;
        } catch (final Exception ex) {
            throw new SQLException("Failed to create sqlQuery into client table", ex);
        }
    }

    @Override
    protected Client mapReadResult(final ResultSet resultSet) throws SQLException {
        try {
            if (!resultSet.next()) {
                return null;
            }

            Client client = null;

            final Integer id = resultSet.getInt("id");
            final String firstName = resultSet.getString("first_name");
            final String lastName = resultSet.getString("last_name");

            if (firstName != null && lastName != null) {
                client = new Client(id, firstName, lastName);
            }

            return client;
        } catch (final Exception ex) {
            throw new SQLException("Failed to read resultSet from client table", ex);
        }
    }
}
