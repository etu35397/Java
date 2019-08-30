package main.java.controller;

import main.java.business.PersonBusiness;
import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.Person;
import main.java.model.join.ShowPersonReduLocalityJoin;

import java.util.ArrayList;

public class PersonController {
    private PersonBusiness personBusiness;

    public PersonController() {
        this.personBusiness = new PersonBusiness();
    }

    public ArrayList<Person> getAllPerson(boolean justIdNameLastName) throws DateComparaisonException, DataAccessException, NumGsmException {
        return personBusiness.getAllPerson(justIdNameLastName);
    }

    public ArrayList<ShowPersonReduLocalityJoin> getOneShowPersonReduLocality(int id) throws DataAccessException {
        ArrayList<ShowPersonReduLocalityJoin> out = new ArrayList<ShowPersonReduLocalityJoin>();
        out.add(personBusiness.getOneShowPersonReduLocality(id));

        return out;
    }

    public ShowPersonReduLocalityJoin getOnePersonwithLocality(Integer id) throws DataAccessException {
        return personBusiness.getOnePersonwithLocality(id);
    }

}
