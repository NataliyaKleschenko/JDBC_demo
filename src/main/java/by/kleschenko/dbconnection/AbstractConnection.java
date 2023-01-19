package by.kleschenko.dbconnection;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractConnection {
    private static Connection connection = null;
    private static final Properties properties = getProperties();

    private AbstractConnection() {

    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("login"),
                        properties.getProperty("password")
                );
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static Properties getProperties() {
        File file = new File("C:\\src\\JDBC_demo\\src\\main\\resources\\db_properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}

