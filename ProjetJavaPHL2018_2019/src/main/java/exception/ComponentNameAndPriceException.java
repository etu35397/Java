package main.java.exception;

public class ComponentNameAndPriceException extends Exception {
    private String message;

    public ComponentNameAndPriceException() {
        message = "Unknown";
    }

    public ComponentNameAndPriceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erreur Listing Pizza"+message;
    }
}
