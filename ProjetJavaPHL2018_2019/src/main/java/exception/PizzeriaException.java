package main.java.exception;

public class PizzeriaException extends Exception {
    private String message;

    public PizzeriaException(String message) {
        this.message = message;
    }

    public PizzeriaException() {
        this("UNKNOWN");
    }

    @Override
    public String getMessage() {
        return "CustomerDataAccessException: "+message;
    }
}
