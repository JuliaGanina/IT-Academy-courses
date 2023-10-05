package by.itacademy.ganina.dao.mapper.model;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {

    T map(ResultSet resultSet) throws ResultSetMapperException;
}
