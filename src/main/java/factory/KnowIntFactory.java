package main.java.factory;

public class KnowIntFactory {

    public static Boolean isInt(String s){
        boolean isNumber = true;
        try {
            Integer num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            isNumber = false;
        }
            return isNumber;
    }

}
