package main.java.model.join;

import java.util.GregorianCalendar;

public class PurchaseOrderJoin {

    private Integer id;
    private String nomReservation;
    private GregorianCalendar dateCommande;
    private GregorianCalendar dateLivraison;
    private String rueLivraison;
    private String numRueLivraison;
    private String boiteLivraison;
    private String villeLivraison;
    private Integer codePostalLivraison;
    private String ruePizzeria;
    private String numRuePizzeria;
    private Integer codePostalPizzeria;
    private String villePizzeria;
    private Integer idPerson;
    private Boolean isPaid;

    public PurchaseOrderJoin() {

    }

    public PurchaseOrderJoin(Integer id, String nomReservation, GregorianCalendar dateCommande, GregorianCalendar dateLivraison, String rueLivraison, String numRueLivraison, String boiteLivraison, String villeLivraison, Integer codePostalLivraison, String ruePizzeria, String numRuePizzeria, Integer codePostalPizzeria, String villePizzeria, Integer idPerson, Boolean isPaid) {
        this.id = id;
        this.nomReservation = nomReservation;
        this.dateCommande = dateCommande;
        this.dateLivraison = dateLivraison;
        this.rueLivraison = rueLivraison;
        this.numRueLivraison = numRueLivraison;
        this.boiteLivraison = boiteLivraison;
        this.villeLivraison = villeLivraison;
        this.codePostalLivraison = codePostalLivraison;
        this.ruePizzeria = ruePizzeria;
        this.numRuePizzeria = numRuePizzeria;
        this.codePostalPizzeria = codePostalPizzeria;
        this.villePizzeria = villePizzeria;
        this.idPerson = idPerson;
        this.isPaid = isPaid;
    }

    public PurchaseOrderJoin(Integer id, String nomReservation, GregorianCalendar dateCommande, GregorianCalendar dateLivraison, String rueLivraison, String numRueLivraison, String boiteLivraison, String villeLivraison, Integer codePostalLivraison, String ruePizzeria, String numRuePizzeria, Integer codePostalPizzeria, String villePizzeria, Integer idPerson) {
        this(id,nomReservation,dateCommande,dateLivraison,rueLivraison,numRueLivraison,boiteLivraison,villeLivraison,codePostalLivraison,ruePizzeria,numRuePizzeria,codePostalPizzeria,villePizzeria,idPerson,null);
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
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

    public String getRueLivraison() { return rueLivraison; }

    public void setRueLivraison(String rueLivraison) {
        this.rueLivraison = rueLivraison;
    }

    public String getNumRueLivraison() {
        return numRueLivraison;
    }

    public void setNumRueLivraison(String numRueLivraison) {
        this.numRueLivraison = numRueLivraison;
    }

    public String getBoiteLivraison() {
        return boiteLivraison;
    }

    public void setBoiteLivraison(String boiteLivraison) {
        this.boiteLivraison = boiteLivraison;
    }

    public String getVilleLivraison() {
        return villeLivraison;
    }

    public void setVilleLivraison(String villeLivraison) {
        this.villeLivraison = villeLivraison;
    }

    public Integer getCodePostalLivraison() {
        return codePostalLivraison;
    }

    public void setCodePostalLivraison(Integer codePostalLivraison) {
        this.codePostalLivraison = codePostalLivraison;
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

    public Integer getCodePostalPizzeria() {
        return codePostalPizzeria;
    }

    public void setCodePostalPizzeria(Integer codePostalPizzeria) {
        this.codePostalPizzeria = codePostalPizzeria;
    }

    public String getVillePizzeria() {
        return villePizzeria;
    }

    public void setVillePizzeria(String villePizzeria) {
        this.villePizzeria = villePizzeria;
    }

    public String getAdressePizzeria() {

        return this.getNumRuePizzeria() + " " + this.getRuePizzeria() + " " + this.getCodePostalPizzeria() + " " + this.getVillePizzeria();
    }

    public String getAdresseLivraison() {
        if (this.getNumRueLivraison().equals(""))
            return "Sur place";

        String adresse = this.getNumRueLivraison() + " " + this.getRueLivraison() + " " + this.getCodePostalLivraison() + " " + this.getVilleLivraison();
        if (this.getBoiteLivraison() != null && (! this.getBoiteLivraison().equals(""))) adresse += " Boite : " + this.getBoiteLivraison();
        return adresse;
    }

    @Override
    public String toString() {
        return "Id : " + this.getId() + " - Nom de réservation : " + this.getNomReservation();
    }
}
