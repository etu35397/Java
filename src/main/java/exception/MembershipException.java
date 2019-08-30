package main.java.exception;

public class MembershipException extends Exception {
    private String message;

    public MembershipException(String message) {
        super(message);
    }

    public MembershipException() {
        this("UNKNOWN");
    }

    @Override
    public String getMessage() {
        return "CustomerDataAccessException: "+message;
    }
}
