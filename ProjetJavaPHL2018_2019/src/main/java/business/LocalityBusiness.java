package main.java.business;

import main.java.DAO.ILocalityDataAccess;
import main.java.dataAccess.LocalityDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.LocalityException;
import main.java.model.Locality;

import java.util.ArrayList;

public class LocalityBusiness {
    private ILocalityDataAccess localiteDataAccess;

    public LocalityBusiness() {
        setLocaliteDataAccess(new LocalityDataAccess());
    }

    public void setLocaliteDataAccess(LocalityDataAccess localiteDataAccess) {
        this.localiteDataAccess = localiteDataAccess;
    }

    public ArrayList<Locality> getAllLocality() throws DataAccessException {
        return localiteDataAccess.getAllLocality();
    }
}
