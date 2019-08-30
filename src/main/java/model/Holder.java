package main.java.model;

import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;

import java.util.GregorianCalendar;

public class Holder extends Person {
    private String rue;
    private Integer numRue;
    private String boite;
    private String numTel;
    private Integer point;
    private Integer localite;

    public Holder(Integer id, String nom, String prenom, String numGSM, GregorianCalendar dateNaissance, Boolean estHomme, String typePerson, String rue, Integer numRue, String boite, String numTel, Integer point, Integer localite) throws NumGsmException, DateComparaisonException {
        super(id, nom, prenom, numGSM, dateNaissance, estHomme, typePerson);
        this.setRue(rue);
        this.setNumRue(numRue);
        this.setBoite(boite);
        this.setNumTel(numTel);
        this.setPoint(point);
        this.setLocalite(localite);
    }

    public Holder() throws NumGsmException, DateComparaisonException {
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Integer getNumRue() {
        return numRue;
    }

    public void setNumRue(Integer numRue) {
        this.numRue = numRue;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getLocalite() {
        return localite;
    }

    public void setLocalite(Integer localite) {
        this.localite = localite;
    }
}
