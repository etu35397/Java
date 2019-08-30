package main.java.model;

public class Locality {
    private Integer id;
    private Integer codePostal;
    private String nom;

    public Locality(Integer id, Integer codePostal, String nom) {
        this.setId(id);
        this.setCodePostal(codePostal);
        this.setNom(nom);
    }

    public Locality() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.getNom()+" ("+this.getCodePostal()+")";
    }
}
