package main.java.model;

public class Pizza {
    private Integer id;
    private String nom;
    private Integer pate;
    private Integer taille;

    public Pizza(Integer id, String nom, Integer pate, Integer taille) {
        this.setId(id);
        this.setNom(nom);
        this.setPate(pate);
        this.setTaille(taille);
    }

    public Pizza() {
    }

    public Pizza(Integer id, String nom) {
        this(id, nom, null, null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPate() {
        return pate;
    }

    public void setPate(Integer pate) {
        this.pate = pate;
    }

    public Integer getTaille() {
        return taille;
    }

    public void setTaille(Integer taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return getId()+" "+getNom()+" "+getTaille()+" "+getPate();
    }
}
