package main.java.dataAccess;

import main.java.exception.DataAccessException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static Connection connection;

    private DatabaseConnection() throws DataAccessException {
        try {
            String url = "jdbc:mysql://localhost:3306/bdlibiopizza?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B1";
            String username = "root";
            String password = "root";

            DatabaseConnection.connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    public static DatabaseConnection getInstance() throws DataAccessException {
        try {
            if (instance == null) {
                instance = new DatabaseConnection();
            } else if (instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }

            return instance;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
