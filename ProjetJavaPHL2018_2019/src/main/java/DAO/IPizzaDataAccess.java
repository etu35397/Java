package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;
import main.java.model.Size;
import main.java.model.join.PizzaJoin;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface IPizzaDataAccess {

    ArrayList<PizzaJoin> searchPizza(Integer minPrice, Integer maxPrice, ArrayList<ComponentPizza> componentsList, Size size) throws DataAccessException;

    ArrayList<PizzaJoin> searchOnePizza(Integer id) throws DataAccessException;

    ArrayList<PizzaJoin> listingPizzaForOrder() throws DataAccessException;

    ArrayList<PizzaJoin> getPizzaFromPurchaseOrder(Integer id) throws DataAccessException;

    void walkInResultSet(ResultSet rs, ArrayList<PizzaJoin> pizzaList) throws DataAccessException;

    ArrayList<ComponentPizza> searchComponentOfOnePizza(Integer id) throws DataAccessException;
}
