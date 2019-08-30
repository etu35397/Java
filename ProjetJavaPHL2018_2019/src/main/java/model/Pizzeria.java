package main.java.model;

public class Pizzeria {
    private Integer id;
    private String rue;
    private Integer numRue;
    private String boite;
    private Integer localite;

    public Pizzeria(Integer id, String rue, Integer numRue, String boite, Integer localite) {
        this.setId(id);
        this.setRue(rue);
        this.setNumRue(numRue);
        this.setBoite(boite);
        this.setLocalite(localite);
    }

    public Pizzeria() {
    }

    public Integer getLocalite() {
        return localite;
    }

    public void setLocalite(Integer localite) {
        this.localite = localite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
