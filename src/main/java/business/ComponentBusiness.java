package main.java.business;

import main.java.DAO.IComponentDataAccess;
import main.java.dataAccess.ComponentDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;

import java.util.ArrayList;

public class ComponentBusiness {
    private IComponentDataAccess componentDataAccess;

    public ComponentBusiness() {
        setComponentDataAccess(new ComponentDataAccess());
    }

    public ArrayList<ComponentPizza> getComponentNameAndPrice() throws DataAccessException {
        return componentDataAccess.getComponentNameAndPrice();
    }

    public void setComponentDataAccess(ComponentDataAccess componentDataAccess) {
        this.componentDataAccess = componentDataAccess;
    }
}
