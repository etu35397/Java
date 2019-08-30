package main.java.model.join;

public class PizzaJoin {
    private Integer id;
    private String name;
    private String doughName;
    private String sauceName;
    private Double price;


    public PizzaJoin() {
    }

    public PizzaJoin(Integer id, String name, String doughName, String sauceName, Double price) {
        setId(id);
        setName(name);
        setDoughName(doughName);
        setSauceName(sauceName);
        setPrice(price);
    }

    public PizzaJoin(Integer id, String name, String doughName) {
        this(id, name, doughName, null, null);
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

    public String getDoughName() {
        return doughName;
    }

    public void setDoughName(String doughName) {
        this.doughName = doughName;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getName()+" - "+getDoughName()+" - "+getSauceName()+" - "+String.format("%.2f", getPrice())+"€";
    }
}
