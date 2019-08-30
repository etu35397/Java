package main.java.exception;

public class FamilyMemberException extends Exception {
    private String message;

    public FamilyMemberException(String message) {
        this.message = message;
    }

    public FamilyMemberException() {
        this("UNKNOWN");
    }

    public String getMessage() {
        return "CustomerDataAccessException: "+message;
    }
}

