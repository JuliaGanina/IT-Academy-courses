package by.itacademy.ganina.dao.jdbc;

import by.itacademy.ganina.dao.DaoException;
import by.itacademy.ganina.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.ganina.model.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportJdbcDao extends GenericJdbcDao<Transport> {

    public TransportJdbcDao(final JdbcDataSourceProperties properties, final String tableName) throws DaoException {
        super(properties, "transport");
    }

    @Override
    protected PreparedStatement getPreparedStatementToCreate(final Connection connection, final Transport transport) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPreparedStatementToUpdate(final Connection connection, final Transport transport) throws SQLException {
        return null;
    }

    @Override
    protected Transport mapReadResult(final ResultSet resultSet) throws SQLException {
        return null;
    }
}
