package main.java.DAO;

import main.java.dataAccess.DatabaseConnection;
import main.java.exception.DataAccessException;

import java.sql.Connection;

public interface IDatabaseConnection {

    DatabaseConnection getInstance() throws DataAccessException;

    Connection getConnection();
}
