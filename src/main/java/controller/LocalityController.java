package main.java.controller;

import main.java.business.LocalityBusiness;
import main.java.exception.DataAccessException;
import main.java.model.Locality;

import java.util.ArrayList;

public class LocalityController {
    private LocalityBusiness localityBusiness;

    public LocalityController() {
        this.localityBusiness = new LocalityBusiness();
    }

    public ArrayList<Locality> getAllLocality() throws DataAccessException {
        return localityBusiness.getAllLocality();
    }
}
