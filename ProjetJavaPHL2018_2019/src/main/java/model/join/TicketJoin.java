package main.java.model.join;

import main.java.factory.FormatFactory;
import main.java.model.Price;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TicketJoin {
    private ArrayList<PizzaJoin> pizzaList;
    private String name;
    private GregorianCalendar orderDate;
    private GregorianCalendar deliveryDate;
    private Integer personId;
    private Integer point;
    private String numRoadPizzeria;
    private String roadPizzeria;
    private Integer postalCodePizzeria;
    private String cityPizzeria;
    private String boxPizzeria;
    private Integer numRoadPerson;
    private String roadPerson;
    private Integer postalCodePerson;
    private String cityPerson;
    private String boxPerson;
    private Price price;

    public TicketJoin() {
        this.price = new Price();
    }

    public TicketJoin(ArrayList<PizzaJoin> pizzaList, String name, GregorianCalendar orderDate, GregorianCalendar deliveryDate, Integer personId, Integer point, String numRoadPizzeria, String roadPizzeria, Integer postalCodePizzeria, String cityPizzeria, String boxPizzeria, Integer numRoadPerson, String roadPerson, Integer postalCodePerson, String cityPerson, String boxPerson) {
        this.pizzaList = pizzaList;
        this.name = name;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.personId = personId;
        this.point = point;
        this.numRoadPizzeria = numRoadPizzeria;
        this.roadPizzeria = roadPizzeria;
        this.postalCodePizzeria = postalCodePizzeria;
        this.cityPizzeria = cityPizzeria;
        this.boxPizzeria = boxPizzeria;
        this.numRoadPerson = numRoadPerson;
        this.roadPerson = roadPerson;
        this.postalCodePerson = postalCodePerson;
        this.cityPerson = cityPerson;
        this.boxPerson = boxPerson;
        this.price = new Price();
        setPrice();
    }

    public ArrayList<PizzaJoin> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(ArrayList<PizzaJoin> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(GregorianCalendar orderDate) {
        this.orderDate = orderDate;
    }

    public GregorianCalendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(GregorianCalendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getNumRoadPizzeria() {
        return numRoadPizzeria;
    }

    public void setNumRoadPizzeria(String numRoadPizzeria) {
        this.numRoadPizzeria = numRoadPizzeria;
    }

    public String getRoadPizzeria() {
        return roadPizzeria;
    }

    public void setRoadPizzeria(String roadPizzeria) {
        this.roadPizzeria = roadPizzeria;
    }

    public Integer getPostalCodePizzeria() {
        return postalCodePizzeria;
    }

    public void setPostalCodePizzeria(Integer postalCodePizzeria) {
        this.postalCodePizzeria = postalCodePizzeria;
    }

    public String getCityPizzeria() {
        return cityPizzeria;
    }

    public void setCityPizzeria(String cityPizzeria) {
        this.cityPizzeria = cityPizzeria;
    }

    public String getBoxPizzeria() {
        return boxPizzeria;
    }

    public void setBoxPizzeria(String boxPizzeria) {
        this.boxPizzeria = boxPizzeria;
    }

    public Integer getNumRoadPerson() {
        return numRoadPerson;
    }

    public void setNumRoadPerson(Integer numRoadPerson) {
        this.numRoadPerson = numRoadPerson;
    }

    public String getRoadPerson() {
        return roadPerson;
    }

    public void setRoadPerson(String roadPerson) {
        this.roadPerson = roadPerson;
    }

    public Integer getPostalCodePerson() {
        return postalCodePerson;
    }

    public void setPostalCodePerson(Integer postalCodePerson) {
        this.postalCodePerson = postalCodePerson;
    }

    public String getCityPerson() {
        return cityPerson;
    }

    public void setCityPerson(String cityPerson) {
        this.cityPerson = cityPerson;
    }

    public String getBoxPerson() {
        return boxPerson;
    }

    public void setBoxPerson(String boxPerson) {
        this.boxPerson = boxPerson;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice() {
        for (PizzaJoin pizza : getPizzaList()) {
            setPriceHTVA(pizza.getPrice());
        }
    }

    public void setPriceHTVA(Double priceToAdd){
        price.add(priceToAdd);
    }

    public String getAdressPizzeria() {
        return this.getCityPizzeria() + " " + this.getRoadPizzeria() + " N°" + this.getNumRoadPizzeria() + " " + (this.getBoxPizzeria()!=null?" BTE " + this.getBoxPizzeria():"") + "(" + this.getPostalCodePizzeria() + ")";
    }

    public String getAdressPerson() {
        if (this.getRoadPerson().equals(""))
            return "Sur place";
        return this.getCityPerson() + " " + this.getRoadPerson() + " N°" + this.getNumRoadPerson() + (this.getBoxPerson()!=null?" BTE " + this.getBoxPerson():"") + " (" + this.getPostalCodePerson() + ")";
    }



    @Override
    public String toString() {
        String infosTicket = "Infos Relative au ticket :"+
                FormatFactory.FORMAT_SUB_ELEMENT+"Nom : " + this.getName() +
                FormatFactory.FORMAT_SUB_ELEMENT+"Date de la commande : " + (this.getOrderDate()==null?" ? " : this.getOrderDate().getTime()) +
                FormatFactory.FORMAT_SUB_ELEMENT+"Date de la livraison : " + (this.getDeliveryDate()==null?" ? " : this.getDeliveryDate().getTime()) +
                FormatFactory.FORMAT_SUB_ELEMENT+"Adresse de la livraison : " + this.getAdressPerson() +
                FormatFactory.FORMAT_SUB_ELEMENT+"Points  du titulaire : " +  (this.getPoint()==null || this.getPoint()<0 ? " ? (Invite) " : this.getPoint())+ FormatFactory.FORMAT_SUB_ELEMENT+
                FormatFactory.NEWLINE+"Infos relative à la pizzeria : " +
                FormatFactory.NEWLINE +"Adresse de la pizzeria : " +
                FormatFactory.FORMAT_SUB_ELEMENT+this.getAdressPizzeria() + FormatFactory.NEWLINE +
                FormatFactory.NEWLINE+"Pizza(s) commandée(s) :";

        for (PizzaJoin pizza: pizzaList) {
            infosTicket += (FormatFactory.FORMAT_SUB_ELEMENT+pizza);
        }

        infosTicket+=FormatFactory.NEWLINE;

        infosTicket += FormatFactory.NEWLINE+"Prix totale de la commande : " + this.getPrice();
        return infosTicket;
    }
}
