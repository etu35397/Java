package main.java.business;

import main.java.DAO.ISizeDataAccess;
import main.java.dataAccess.SizeDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.Size;

import java.util.ArrayList;

public class SizeBusiness {
    private ISizeDataAccess sizeDataAccess;

    public SizeBusiness() {
        setSizeDataAccess(new SizeDataAccess());
    }

    public void setSizeDataAccess(SizeDataAccess sizeDataAccess) {
        this.sizeDataAccess = sizeDataAccess;
    }

    public ArrayList<Size> getSizeNameAndPrice() throws DataAccessException {
        return sizeDataAccess.getSizeNameAndPrice();
    }

}
