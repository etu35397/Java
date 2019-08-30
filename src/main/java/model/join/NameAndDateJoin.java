package main.java.model.join;

import java.util.GregorianCalendar;

public class NameAndDateJoin {

    private String nom;
    private String prénom;
    GregorianCalendar dateCommande;

    public NameAndDateJoin() {
    }

    public NameAndDateJoin(String nom, String prénom, GregorianCalendar dateCommande) {
        this.nom = nom;
        this.prénom = prénom;
        this.dateCommande = dateCommande;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public GregorianCalendar getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(GregorianCalendar dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Override
    public String toString() {
        return this.nom+" "+this.prénom+ " Date de commande : " + this.dateCommande;
    }
}
