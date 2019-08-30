package main.java.model.join;

public class PizzeriaAdressJoin {
    private Integer id;
    private String numRoad;
    private String road;
    private String boite;

    public PizzeriaAdressJoin() {
    }

    public PizzeriaAdressJoin(Integer id, String numRoad, String road, String boite) {
        setId(id);
        setNumRoad(numRoad);
        setBoite(boite);
        setRoad(road);
    }

    public String getNumRoad() {
        return numRoad;
    }

    public void setNumRoad(String numRoad) {
        this.numRoad = numRoad;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    @Override
    public String toString() {
        return road+", "+ numRoad+ (boite==null?"":" BTE "+boite);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
