package main.java.model.join;

public class ShowPersonReduLocalityJoin {
    private String nom;
    private String prenom;
    private String numGSM;
    private Boolean estHomme;
    private String rue;
    private Integer numRue;
    private String boite;
    private String numTel;
    private Integer codePostal;
    private String ville;
    private Integer point;
    private String typePers;

    public ShowPersonReduLocalityJoin(String nom, String prenom, String numGSM, Boolean estHomme, String rue, Integer numRue, String boite, String numTel, Integer point, Integer codePostal, String ville, String typePers) {
        setNom(nom);
        setPrenom(prenom);
        setNumGSM(numGSM);
        setEstHomme(estHomme);
        setRue(rue);
        setNumRue(numRue);
        setBoite(boite);
        setNumTel(numTel);
        setPoint(point);
        setCodePostal(codePostal);
        setVille(ville);
        setTypePers(typePers);
    }

    public ShowPersonReduLocalityJoin() {
    }

    public String getTypePers() {
        return typePers;
    }

    public void setTypePers(String typePers) {
        this.typePers = typePers;
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

    public Boolean getEstHomme() {
        return estHomme;
    }

    public String getRue() {
        return rue;
    }

    public Integer getNumRue() {
        return numRue;
    }

    public String getBoite() {
        return boite;
    }

    public String getNumTel() {
        return numTel;
    }

    public Integer getPoint() {
        return point;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumGSM(String numGSM) {
        this.numGSM = numGSM;
    }

    public void setEstHomme(Boolean estHomme) {
        this.estHomme = estHomme;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setNumRue(Integer numRue) {
        this.numRue = numRue;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
