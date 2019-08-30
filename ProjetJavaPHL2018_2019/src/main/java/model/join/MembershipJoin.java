package main.java.model.join;

import main.java.exception.MembershipException;
import main.java.model.Pizza;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MembershipJoin {
    private Integer id;
    private String nomReservation;
    private GregorianCalendar dateCommande;
    private GregorianCalendar dateLivraison;
    private String rue;
    private String numRue;
    private String boite;
    private Integer localite;
    private Boolean estPaye;
    private Integer pizzeria;
    private String ruePizzeria;
    private String numRuePizzeria;
    private String boitePizzeria;
    private PizzeriaCityJoin cityPizzeria;
    private Integer personne;
    private ArrayList<PizzaJoin> pizzaList;

    public MembershipJoin() {
    }

    public MembershipJoin(Integer id, String nomReservation, GregorianCalendar dateCommande, GregorianCalendar dateLivraison, String rue, String numRue, String boite, Integer localite, Boolean estPaye, Integer pizzeria, String ruePizzeria, String numRuePizzeria, String boitePizzeria, PizzeriaCityJoin cityPizzeria, Integer personne, ArrayList<PizzaJoin> pizzaList) {
        this.id = id;
        this.nomReservation = nomReservation;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.rue = rue;
        this.numRue = numRue;
        this.boite = boite;
        this.localite = localite;
        this.estPaye = estPaye;
        this.pizzeria = pizzeria;
        this.ruePizzeria = ruePizzeria;
        this.numRuePizzeria = numRuePizzeria;
        this.boitePizzeria = boitePizzeria;
        this.cityPizzeria = cityPizzeria;
        this.personne = personne;
        this.pizzaList = pizzaList;
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

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public Integer getLocalite() {
        return localite;
    }

    public void setLocalite(Integer localite) {
        this.localite = localite;
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

    public String getRuePizzeria() {
        return ruePizzeria;
    }

    public void setRuePizzeria(String ruePizzeria) {
        this.ruePizzeria = ruePizzeria;
    }

    public String getNumRuePizzeria() {
        return numRuePizzeria;
    }

    public void setNumRuePizzeria(String numRuePizzeria) {
        this.numRuePizzeria = numRuePizzeria;
    }

    public String getBoitePizzeria() {
        return boitePizzeria;
    }

    public void setBoitePizzeria(String boitePizzeria) {
        this.boitePizzeria = boitePizzeria;
    }

    public PizzeriaCityJoin getCityPizzeria() {
        return cityPizzeria;
    }

    public void setCityPizzeria(PizzeriaCityJoin cityPizzeria) {
        this.cityPizzeria = cityPizzeria;
    }

    public Integer getPersonne() {
        return personne;
    }

    public void setPersonne(Integer personne) {
        this.personne = personne;
    }

    public ArrayList<PizzaJoin> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(ArrayList<PizzaJoin> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @Override
    public String toString() {
        return "MembershipJoin{" +
                "id=" + id +
                ", nomReservation='" + nomReservation + '\'' +
                ", dateCommande=" + dateCommande.getTime() +
                ", dateLivraison=" + dateLivraison.getTime() +
                ", rue='" + rue + '\'' +
                ", numRue=" + numRue +
                ", boite='" + boite + '\'' +
                ", localite=" + localite +
                ", estPaye=" + estPaye +
                ", pizzeria=" + pizzeria +
                ", ruePizzeria='" + ruePizzeria + '\'' +
                ", numRuePizzeria=" + numRuePizzeria +
                ", boitePizzeria='" + boitePizzeria + '\'' +
                ", cityPizzeria=" + cityPizzeria +
                ", personne=" + personne +
                ", pizzaList=" + pizzaList +
                '}';
    }
}
