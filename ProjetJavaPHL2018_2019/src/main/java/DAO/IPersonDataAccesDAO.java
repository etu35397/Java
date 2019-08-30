package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.Person;
import main.java.model.join.ShowPersonReduLocalityJoin;

import java.util.ArrayList;

public interface IPersonDataAccesDAO {

    ArrayList<Person> getAll(boolean justIdNameLastName) throws DataAccessException, NumGsmException, DateComparaisonException;

    ShowPersonReduLocalityJoin getOneShowPersonReduLocality(int id) throws DataAccessException;

    ShowPersonReduLocalityJoin getOnePersonwithLocality(int id) throws DataAccessException;

    int delete(Person person) throws DataAccessException;

}
