package by.itacademy.ganina.dao;

import by.itacademy.ganina.dao.datasource.DataSourcePropertiesFactory;
import by.itacademy.ganina.dao.datasource.DataSourcePropertiesFactoryException;
import by.itacademy.ganina.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.ganina.dao.datasource.impl.FileDataSourcePropertiesFactory;
import by.itacademy.ganina.dao.jdbc.ClientJdbcDao;


public class AutoserviceJdbcDaoApplication {
    public static void main(final String[] args) throws DataSourcePropertiesFactoryException, DaoException {

        final DataSourcePropertiesFactory propertiesFactory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = propertiesFactory.create("application.properties");
        final Dao dao = new ClientJdbcDao(properties, "client");
        System.out.println(dao.read(4));
      //  System.out.println(dao.delete(9));
      //
        // System.out.println(dao.create( new Client (0, "Gregory", "Moon")));

    }
}
