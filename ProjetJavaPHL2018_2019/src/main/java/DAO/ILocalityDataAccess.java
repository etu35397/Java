package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.model.Locality;

import java.util.ArrayList;

public interface ILocalityDataAccess {

    ArrayList<Locality> getAllLocality() throws DataAccessException;
}
