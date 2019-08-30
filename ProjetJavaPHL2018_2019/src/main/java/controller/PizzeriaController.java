package main.java.controller;

import main.java.business.PizzeriaBusiness;
import main.java.exception.DataAccessException;
import main.java.exception.PizzeriaException;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PizzeriaController {
    private PizzeriaBusiness pizzeriaBusiness;

    public PizzeriaController() {
        setPizzeriaBusiness(new PizzeriaBusiness());
    }

    public void setPizzeriaBusiness(PizzeriaBusiness pizzeriaBusiness) {
        this.pizzeriaBusiness = pizzeriaBusiness;
    }

    public ArrayList<PizzeriaCityJoin> getAllTownPizzeria() throws DataAccessException {
        return pizzeriaBusiness.getAllTownPizzeria();
    }

    public ArrayList<PizzeriaAdressJoin> getAdressPizzeria(Integer id) throws PizzeriaException, DataAccessException {
        return pizzeriaBusiness.getAdressPizzeria(id);
    }

    public ArrayList<PizzeriaAdressJoin> getAllPizzeria() throws PizzeriaException, DataAccessException {
        return pizzeriaBusiness.getAllPizzeria();
    }

    public Double getAveragePrice(Integer id, GregorianCalendar dateDebut, GregorianCalendar dateFin) throws PizzeriaException, DataAccessException {
        return pizzeriaBusiness.getAveragePrice(id, dateDebut, dateFin);
    }
}
