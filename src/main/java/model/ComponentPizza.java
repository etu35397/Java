package main.java.model;

import java.io.Serializable;

public class ComponentPizza implements Serializable {
    private Integer id;
    private String name;
    private String taste;
    private Double price;

    public ComponentPizza(Integer id, String name, String taste, Double price) {
        this.setId(id);
        this.setName(name);
        this.setTaste(taste);
        this.setPrice(price);
    }

    public ComponentPizza() {
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

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
