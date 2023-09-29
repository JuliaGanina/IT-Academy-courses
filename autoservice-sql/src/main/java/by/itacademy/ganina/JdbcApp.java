package by.itacademy.ganina;

import by.itacademy.ganina.model.client.Client;
import by.itacademy.ganina.model.transport.Transport;
import by.itacademy.ganina.model.transport.TransportType;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JdbcApp {

    public static void main(String[] args) throws IOException {

        final Properties properties = getProperties("src/application.properties");

        try (final Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            final String transportQuery = "SELECT model.model_name, type.type_name, c.first_name, c.last_name " +
                    "FROM public.transport AS t " +
                    "INNER JOIN public.model_type AS model ON t.model_type_id=model.id " +
                    "INNER JOIN public.transport_type AS type ON t.transport_type_id=type.id " +
                    "LEFT JOIN public.client AS c ON t.client_id=c.id";
            final PreparedStatement statement = connection.prepareStatement(transportQuery);

            final ResultSet transportRS = statement.executeQuery();
            while (transportRS.next()) {
                final String model = transportRS.getString("model_name");
                final TransportType transportType = TransportType.valueOf(transportRS.getString("type_name"));

                Client client = null;
                final String firstName = transportRS.getString("first_name");
                final String lastName = transportRS.getString("last_name");
                if (firstName != null && lastName != null) {
                    client = new Client(firstName, lastName);
                }

                final Transport transport = new Transport(model, transportType, client);
                System.out.println(transport);
            }
        } catch (final SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static Properties getProperties(final String fileName) throws IOException {
        final Properties properties = new Properties();
        try (final FileReader reader = new FileReader(fileName)) {
            properties.load(reader);
            return properties;
        }
    }
}