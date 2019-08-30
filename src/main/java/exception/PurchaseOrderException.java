package main.java.exception;

public class PurchaseOrderException extends Exception{
    private String message;

    public PurchaseOrderException(String message) {
        this.message = message;
    }

    public PurchaseOrderException() {
        this("UNKNOWN");
    }

    @Override
    public String getMessage() {
        return "CustomerDataAccessException: "+message;
    }
}
