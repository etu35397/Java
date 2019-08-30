package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;

import java.util.ArrayList;

public interface IComponentDataAccess {
    ArrayList<ComponentPizza> getComponentNameAndPrice() throws DataAccessException;
}
