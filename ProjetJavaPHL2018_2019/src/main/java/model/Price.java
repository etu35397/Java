package main.java.model;

import main.java.factory.FormatFactory;

public class Price {
    private Double priceHTVA;
    private Double tva;
    private Double priceTVAC;

    public Price(Double priceHTVA) {
        setPriceHTVA(priceHTVA);
    }

    public Price() {
        setPriceHTVA(0.0);
    }


    public void add(Double priceToAdd){
        if(priceToAdd!=null) {
            setPriceHTVA(this.priceHTVA + priceToAdd);
        }
    }

    public Double getPriceHTVA() {
        return priceHTVA;
    }

    public void setPriceHTVA(Double priceHTVA) {
        this.priceHTVA = priceHTVA;
        setTva(priceHTVA);
        setPriceTVAC(priceHTVA);

    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double price) {
        this.tva = price*0.06;
    }

    public Double getPriceTVAC() {
        return priceTVAC;
    }

    public void setPriceTVAC(Double price) {
        this.priceTVAC = price+tva;
    }

    @Override
    public String toString() {

        return  FormatFactory.FORMAT_SUB_ELEMENT+"Prix HTVA :  "+String.format("%.2f", getPriceHTVA())+"€" +
                FormatFactory.FORMAT_SUB_ELEMENT+"TVA :  "+String.format("%.2f", getTva())+"€"+
                FormatFactory.FORMAT_SUB_ELEMENT+"PRIX TVAC :  "+String.format("%.2f", getPriceTVAC())+"€";
    }
}
