package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.model.Size;

import java.util.ArrayList;

public interface ISizeDataAccess {

    ArrayList<Size> getSizeNameAndPrice() throws DataAccessException;
}
