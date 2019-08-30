package main.java.model;

import java.util.GregorianCalendar;

public class Order {
    private Integer id;
    private String nomReservation;
    private GregorianCalendar dateCommande;
    private GregorianCalendar dateLivraison;
    private String rue;
    private String numRue;
    private String boite;
    private Boolean estPaye;
    private Integer pizzeria;
    private Integer localite;
    private Integer personne;

    public Order(Integer id, String nomReservation, GregorianCalendar dateCommande, GregorianCalendar dateLivraison, String rue, String numRue, String boite, Boolean estPaye, Integer pizzeria, Integer localite, Integer personne) {
        this.setId(id);
        this.setNomReservation(nomReservation);
        this.setDateCommande(dateCommande);
        this.setDateLivraison(dateLivraison);
        this.setRue(rue);
        this.setNumRue(numRue);
        this.setBoite(boite);
        this.setEstPaye(estPaye);
        this.setPizzeria(pizzeria);
        this.setLocalite(localite);
        this.setPersonne(personne);
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomReservation() {
        return nomReservation;
    }

    public void setNomReservation(String nomReservation) {
        this.nomReservation = nomReservation;
    }

    public GregorianCalendar getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(GregorianCalendar dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public GregorianCalendar getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(GregorianCalendar dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumRue() {
        return numRue;
    }

    public void setNumRue(String numRue) {
        this.numRue = numRue;
    }

    public Boolean getEstPaye() {
        return estPaye;
    }

    public void setEstPaye(Boolean estPaye) {
        this.estPaye = estPaye;
    }

    public Integer getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(Integer pizzeria) {
        this.pizzeria = pizzeria;
    }

    public Integer getLocalite() {
        return localite;
    }

    public void setLocalite(Integer localite) {
        this.localite = localite;
    }

    public Integer getPersonne() {
        return personne;
    }

    public void setPersonne(Integer personne) {
        this.personne = personne;
    }
}
