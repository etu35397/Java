package main.java.exception;

public class NumGsmException extends Exception {
    private String numGsm;
    private static String FORMAT = "04NN NN NN NN";

    public NumGsmException(String numGsm) {
        this.numGsm = numGsm;
    }
    public String getMessage(){
        return "le numero de GSM doit etre au format: "+FORMAT+"\n numero inscrit: "+numGsm;
    }
}
