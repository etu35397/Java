package main.java.controller;

import main.java.business.SizeBusiness;
import main.java.exception.DataAccessException;
import main.java.model.Size;

import java.util.ArrayList;

public class SizeContoller {
    private SizeBusiness sizeBusiness;

    public SizeContoller() {
        setSizeBusiness(new SizeBusiness());
    }

    public void setSizeBusiness(SizeBusiness sizeBusiness) {
        this.sizeBusiness = sizeBusiness;
    }

    public SizeBusiness getSizeBusiness() {
        return sizeBusiness;
    }

    public ArrayList<Size> getSizeNameAndPrice() throws DataAccessException {
        return this.sizeBusiness.getSizeNameAndPrice();
    }
}
