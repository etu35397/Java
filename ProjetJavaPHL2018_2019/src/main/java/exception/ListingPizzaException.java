package main.java.exception;

public class ListingPizzaException extends Exception{
    private String message;

    public ListingPizzaException() {
        message = "Unknown";
    }

    public ListingPizzaException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erreur Listing Pizza"+message;
    }
}
