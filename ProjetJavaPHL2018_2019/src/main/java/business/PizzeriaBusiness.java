package main.java.business;

import main.java.DAO.IPizzeriaDataAccess;
import main.java.dataAccess.PizzeriaDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.PizzeriaException;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PizzeriaBusiness {
    IPizzeriaDataAccess pizzeriaDataAccess;

    public PizzeriaBusiness() {
        setPizzeriaDataAccess(new PizzeriaDataAccess());
    }

    public void setPizzeriaDataAccess(PizzeriaDataAccess pizzeriaDataAccess) {
        this.pizzeriaDataAccess = pizzeriaDataAccess;
    }

    public ArrayList<PizzeriaCityJoin> getAllTownPizzeria() throws DataAccessException {
        return pizzeriaDataAccess.getAllTownPizzeria();
    }

    public ArrayList<PizzeriaAdressJoin> getAdressPizzeria(Integer id) throws PizzeriaException, DataAccessException {
        return pizzeriaDataAccess.getAdressPizzeria(id);
    }

    public ArrayList<PizzeriaAdressJoin> getAllPizzeria() throws PizzeriaException, DataAccessException {
        return pizzeriaDataAccess.getAllPizzeria();
    }

    public Double getAveragePrice(Integer idPizzeria, GregorianCalendar dateDebut, GregorianCalendar dateFin) throws PizzeriaException, DataAccessException {
        return pizzeriaDataAccess.getAveragePrice(idPizzeria, dateDebut, dateFin);
    }
}
