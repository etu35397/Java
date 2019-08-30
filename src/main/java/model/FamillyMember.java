package main.java.model;

import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;

import java.util.GregorianCalendar;

public class FamillyMember extends Person {

    private Integer titulaire;

    public FamillyMember(Integer id, String nom, String prenom, String numGSM, GregorianCalendar dateNaissance, Boolean estHomme,String typePerson, Integer titulaire) throws NumGsmException, DateComparaisonException {
        super(id, nom, prenom, numGSM, dateNaissance, estHomme,typePerson);
        this.setTitulaire(titulaire);
    }

    public FamillyMember() throws NumGsmException, DateComparaisonException {
    }

    public Integer getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(Integer titulaire) {
        this.titulaire = titulaire;
    }
}
