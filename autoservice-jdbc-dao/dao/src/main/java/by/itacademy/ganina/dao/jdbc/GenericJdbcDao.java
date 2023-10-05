package by.itacademy.ganina.dao.jdbc;

import by.itacademy.ganina.dao.Dao;
import by.itacademy.ganina.dao.DaoException;
import by.itacademy.ganina.dao.datasource.JdbcDataSourceProperties;
import org.postgresql.Driver;

import java.lang.reflect.Field;
import java.sql.*;


public abstract class GenericJdbcDao<T> implements Dao<T> {

    private final JdbcDataSourceProperties properties;
    private final String tableName;

    public GenericJdbcDao(final JdbcDataSourceProperties properties, final String tableName) throws DaoException {
        this.properties = properties;
        this.tableName = tableName;

        registerDriver(properties.getDriver());
    }

    protected abstract PreparedStatement getPreparedStatementToCreate(Connection connection, T model) throws SQLException;

    protected abstract PreparedStatement getPreparedStatementToUpdate(Connection connection, T model) throws SQLException;

    protected abstract T mapReadResult(ResultSet resultSet) throws SQLException;

    @Override
    public Integer create(final T model) throws DaoException {
        try (final Connection connection = createConnection()) {
            final PreparedStatement preparedStatement = getPreparedStatementToCreate(connection, model);

            return preparedStatement.executeUpdate();
        } catch (final Exception ex) {
            throw new DaoException("Failed to create: " + model, ex);
        }
    }

    @Override
    public T read(final Integer id) throws DaoException {
        try (final Connection connection = createConnection()) {
            final String readSql = "SELECT * FROM %s WHERE id=?".formatted(tableName);
            final PreparedStatement preparedStatement = connection.prepareStatement(readSql);
            preparedStatement.setInt(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();
            final T model = mapReadResult(resultSet);

            return model;
        } catch (final Exception ex) {
            throw new DaoException("Failed to read object with id: " + id + " from " + tableName, ex);
        }
    }

    @Override
    public Integer update(final T model) throws DaoException {
     /*   try {
            final Field idField = model.getClass().getDeclaredField("id");
            Integer idUpdate = null;
            if (!idField.canAccess(model) && !idField.trySetAccessible()) {
                idUpdate = idField.getInt(model);
            }*/

        try (final Connection connection = createConnection()) {
            final PreparedStatement preparedStatement = getPreparedStatementToUpdate(connection, model);

            return preparedStatement.executeUpdate();
        } catch (final Exception ex) {
            throw new DaoException("Failed to update: " + model, ex);
        }
    }

    @Override
    public Integer delete(final Integer id) throws DaoException {
        try (final Connection connection = createConnection()) {
            final String deleteSql = "DELETE FROM %s WHERE id=?".formatted(tableName);
            final PreparedStatement preparedStatement = connection.prepareStatement(deleteSql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate();
        } catch (final Exception ex) {
            throw new DaoException("Failed to delete object with id: " + id + "from " + tableName, ex);
        }
    }

    private Connection createConnection() throws SQLException {
        try {
            final Connection connection = DriverManager.getConnection(
                    properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword());

            return connection;
        } catch (final Exception ex) {
            throw new SQLException("Failed to connect to DB with url: " + properties.getUrl(), ex);
        }
    }

    private static void registerDriver(final String driverName) throws DaoException {
        try {
            final Class<?> driverClass = Class.forName(driverName);
            final boolean isNotRegistered = DriverManager.drivers().noneMatch(dr -> dr.getClass().equals(driverClass));
            if (isNotRegistered) {
                Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(driver);
            }
        } catch (final Exception ex) {
            throw new DaoException("Failed to registered driver: " + driverName, ex);
        }
    }
}
