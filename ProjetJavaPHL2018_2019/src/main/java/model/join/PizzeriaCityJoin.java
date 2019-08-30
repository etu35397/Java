package main.java.model.join;

public class PizzeriaCityJoin {
    private Integer id;
    private String name;

    public PizzeriaCityJoin(Integer id , String name) {
        setId(id);
        setName(name);
    }

    public PizzeriaCityJoin() {
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

    @Override
    public String toString() {
        return name;
    }
}
