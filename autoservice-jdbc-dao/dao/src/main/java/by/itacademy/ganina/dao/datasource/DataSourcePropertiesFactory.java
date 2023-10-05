package by.itacademy.ganina.dao.datasource;

public interface DataSourcePropertiesFactory {

    JdbcDataSourceProperties create(String source) throws DataSourcePropertiesFactoryException;
}
