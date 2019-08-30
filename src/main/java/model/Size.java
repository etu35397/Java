package main.java.model;

public class Size {
    private Integer id;
    private String name;
    private Double price;

    public Size(Integer id, String name, Double cost) {
        this.setId(id);
        this.setName(name);
        this.setPrice(cost);
    }

    public Size() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
