package main.java.exception;

public class PersonDataAccesException extends Exception {
    private String message;

    public PersonDataAccesException(String message) {
        this.message = message;
    }

    public PersonDataAccesException() {
        this("UNKNOWN");
    }

    @Override
    public String getMessage() {
        return "CustomerDataAccessException: "+message;
    }
}
