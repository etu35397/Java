package main.TestUnitaire;

import main.java.model.join.MembershipJoin;
import main.java.model.join.PizzaJoin;
import main.java.model.join.PizzeriaCityJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public final class Utils {
    public Utils(){}

    public static String generateLongString(int nbCharAMettre){
        String str = "";
        for(int i=0 ; i<nbCharAMettre ;i++){
            str += "A";
        }
        return str;
    }

    public static MembershipJoin createFakeMembershipJoin() {

        ArrayList<PizzaJoin> pizzaList = new ArrayList<PizzaJoin>();
        pizzaList.add(new PizzaJoin(1, "Mozza", "Molle", "Bolognaise", 12.50));
        pizzaList.add(new PizzaJoin(2, "Scampi", "dur", "tomate", 15.00));

        return new MembershipJoin(
                1,
                "Réservation de Luc",
                new GregorianCalendar(2019,8,20, 20, 15),
                new GregorianCalendar(2019,8,20, 21,00),
                "Rue du marbre",
                "4C",
                "10",
                0,
                false,
                2,
                "Rue des êtres",
                "12",
                null,
                new PizzeriaCityJoin(1, "Pizzeria Martini"),
                5,
                pizzaList
        );
    }

}