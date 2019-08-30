package main.java.business;

import main.java.DAO.IPersonDataAccesDAO;
import main.java.dataAccess.PersonDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.Person;
import main.java.model.join.ShowPersonReduLocalityJoin;

import java.util.ArrayList;

public class PersonBusiness {
    private IPersonDataAccesDAO personDataAccess ;

    public PersonBusiness() {
        personDataAccess = new PersonDataAccess();
    }

    public ArrayList<Person> getAllPerson(boolean justIdNameLastName) throws DateComparaisonException, DataAccessException, NumGsmException{
        return personDataAccess.getAll(justIdNameLastName);
    }

    public ShowPersonReduLocalityJoin getOneShowPersonReduLocality(int id) throws DataAccessException {
        return personDataAccess.getOneShowPersonReduLocality(id);

    }
    public ShowPersonReduLocalityJoin getOnePersonwithLocality(Integer id) throws DataAccessException {
        return personDataAccess.getOnePersonwithLocality(id);
    }


}
