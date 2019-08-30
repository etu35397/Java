package main.java.controller;


import main.java.business.ComponentBusiness;
import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;

import java.util.ArrayList;

public class ComponentController {
    private ComponentBusiness componentBusiness;

    public ComponentController() {
        setComponentBusiness(new ComponentBusiness());
    }

    public void setComponentBusiness(ComponentBusiness componentBusiness) {
        this.componentBusiness = componentBusiness;
    }

    public ArrayList<ComponentPizza> getComponentNameAndPrice() throws DataAccessException {
        return componentBusiness.getComponentNameAndPrice();
    }
}
