package main.java.exception;

public class LocalityException extends Throwable {
    private String message;

    public LocalityException(String message) {
        this.message = message;
    }

    public LocalityException() {
        this("UNKNOWN");
    }

    public String getMessage() {
        return "CustomerDataAccessException: "+message;
    }
}
