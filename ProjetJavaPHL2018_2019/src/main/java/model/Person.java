package main.java.model;

import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;

import java.util.GregorianCalendar;

public class Person {
    private Integer id;
    private String nom;
    private String prenom;
    private String numGSM;
    private GregorianCalendar dateNaissance;
    private Boolean estHomme;
    private String typePerson;

    public Person(Integer id, String nom, String prenom , String numGSM, GregorianCalendar dateNaissance, Boolean estHomme, String typePerson) throws NumGsmException, DateComparaisonException {
        setId(id);
        setDateNaissance(dateNaissance);
        setEstHomme(estHomme);
        setNom(nom);
        setPrenom(prenom);
        setNumGSM(numGSM);
        setTypePerson(typePerson);
    }

    public Person() throws NumGsmException, DateComparaisonException {
        this(null,null,null,null,null,null,null);
    }

    public String getTypePerson() {
        return typePerson;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
    }

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumGSM() {
        return numGSM;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public Boolean getEstHomme() {
        return estHomme;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumGSM(String numGSM) {/*throws NumGsmException {

        if (numGSM != null || !numGSM.matches("04\\d{8}")){
            throw new NumGsmException(numGSM);
        }
        */
        this.numGSM = numGSM;
    }

    public String getNomPrenom() {
        return this.nom + " " + this.prenom;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance){ /*throws DateComparaisonException {
        if (dateNaissance!=null || ( dateNaissance.compareTo((new GregorianCalendar())) > 0 ? true : false )){
            throw new DateComparaisonException(dateNaissance);
        }*/
        this.dateNaissance = dateNaissance;
    }

    public void setEstHomme(Boolean estHomme) {
        this.estHomme = estHomme;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return nom+" "+prenom+ " (id : "+id+")";
    }
}
