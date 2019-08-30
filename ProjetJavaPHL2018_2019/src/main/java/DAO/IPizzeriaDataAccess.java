package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.exception.PizzeriaException;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IPizzeriaDataAccess {

    ArrayList<PizzeriaCityJoin> getAllTownPizzeria() throws DataAccessException;

    ArrayList<PizzeriaAdressJoin> getAdressPizzeria(Integer id) throws PizzeriaException, DataAccessException;

    ArrayList<PizzeriaAdressJoin> getAllPizzeria() throws PizzeriaException, DataAccessException;

    Double getAveragePrice(Integer idPizzeria, GregorianCalendar dateDebut, GregorianCalendar dateFin) throws PizzeriaException, DataAccessException;
}
