package main.java.business;

import main.java.DAO.IPizzaDataAccess;
import main.java.dataAccess.PizzaDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;
import main.java.model.Size;
import main.java.model.join.PizzaJoin;

import java.util.ArrayList;

public class PizzaBusiness {

    private IPizzaDataAccess pizzaDataAccess;

    public PizzaBusiness() {this.pizzaDataAccess = new PizzaDataAccess();}

    public ArrayList<PizzaJoin> searchPizza(Integer minValue, Integer maxValue, ArrayList<ComponentPizza> componentPizzaJList, Size size) throws DataAccessException {
        return pizzaDataAccess.searchPizza(minValue, maxValue, componentPizzaJList, size);
    }

    public ArrayList<PizzaJoin> searchOnePizza(Integer id) throws DataAccessException {
        return pizzaDataAccess.searchOnePizza(id);
    }

    public ArrayList<PizzaJoin> listingPizzaForOrder() throws DataAccessException {
        return pizzaDataAccess.listingPizzaForOrder();
    }

    public ArrayList<ComponentPizza> searchComponentOfOnePizza(Integer id) throws DataAccessException {
        return pizzaDataAccess.searchComponentOfOnePizza(id);
    }

    public ArrayList<PizzaJoin> getPizzaFromPurchaseOrder(Integer id) throws DataAccessException {
        return pizzaDataAccess.getPizzaFromPurchaseOrder(id);
    }

}
