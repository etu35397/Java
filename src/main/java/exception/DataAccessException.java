package main.java.exception;

public class DataAccessException extends Exception {
    private String message;

    public DataAccessException() {
        message = "Unknown";
    }

    public DataAccessException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Erreur, Acces Base de Donnée : "+message;
    }
}
