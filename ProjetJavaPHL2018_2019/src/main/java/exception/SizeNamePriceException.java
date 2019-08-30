package main.java.exception;

public class SizeNamePriceException extends Exception {
    private String message;

    public SizeNamePriceException() {
        message = "Unknown";
    }

    public SizeNamePriceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erreur Listing Pizza : "+message;
    }

}
