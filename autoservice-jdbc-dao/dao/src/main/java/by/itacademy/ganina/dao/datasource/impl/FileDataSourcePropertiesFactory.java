package by.itacademy.ganina.dao.datasource.impl;

import by.itacademy.ganina.dao.datasource.DataSourcePropertiesFactory;
import by.itacademy.ganina.dao.datasource.DataSourcePropertiesFactoryException;
import by.itacademy.ganina.dao.datasource.JdbcDataSourceProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileDataSourcePropertiesFactory implements DataSourcePropertiesFactory {

    @Override
    public JdbcDataSourceProperties create(final String source) throws DataSourcePropertiesFactoryException {
        try (final Reader reader = getResource(source)) {
            final Properties properties = new Properties();
            properties.load(reader);

            final String user = properties.getProperty("datasource.jdbc.user");
            final String password = properties.getProperty("datasource.jdbc.password");
            final String url = properties.getProperty("datasource.jdbc.url");
            final String driver = properties.getProperty("datasource.jdbc.driver");

            return new JdbcDataSourceProperties(user, password, url, driver);
        } catch (final IOException ex) {
            throw new DataSourcePropertiesFactoryException("Fail to read properties file", ex);
        }
    }

    private static Reader getResource(final String source) throws DataSourcePropertiesFactoryException {
        final InputStream in = FileDataSourcePropertiesFactory.class.getClassLoader().getResourceAsStream(source);
        if (in != null) {
            return new InputStreamReader(in, StandardCharsets.UTF_8);
        }
        throw new DataSourcePropertiesFactoryException("Resource not found" + source);
    }
}
