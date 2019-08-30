package main.java.controller;

import main.java.business.PizzaBusiness;
import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;
import main.java.model.Size;
import main.java.model.join.PizzaJoin;

import java.util.ArrayList;

public class PizzaController {

    PizzaBusiness pizzaBusiness;

    public PizzaController() {this.pizzaBusiness = new PizzaBusiness();}

    public ArrayList<PizzaJoin> getPizzaMenuListing(Integer minValue, Integer maxValue, ArrayList<ComponentPizza> componentPizzaList, Size size) throws DataAccessException {
        return pizzaBusiness.searchPizza(minValue, maxValue, componentPizzaList, size);
    }

    public ArrayList<PizzaJoin> searchOnePizza(Integer id) throws DataAccessException {
        return pizzaBusiness.searchOnePizza(id);
    }

    public ArrayList<PizzaJoin> listingPizzaForOrder() throws DataAccessException {
        return pizzaBusiness.listingPizzaForOrder();
    }

    public ArrayList<ComponentPizza> searchComponentOfOnePizza(Integer id) throws DataAccessException {
        return pizzaBusiness.searchComponentOfOnePizza(id);
    }

    public ArrayList<PizzaJoin> getPizzaFromPurchaseOrder(Integer id) throws DataAccessException {
        return pizzaBusiness.getPizzaFromPurchaseOrder(id);
    }

}
