package main.java.exception;
import java.util.GregorianCalendar;

public class DateComparaisonException extends Exception{
    private GregorianCalendar datePetite;
    private GregorianCalendar dateGrande;
    private String message;

    public DateComparaisonException(GregorianCalendar datePetite, GregorianCalendar dateGrande,String message) {
        this.datePetite = datePetite;
        this.dateGrande = dateGrande;
        this.message = message;
    }

    public DateComparaisonException(GregorianCalendar datePetite, GregorianCalendar dateGrande) {
        this(datePetite,dateGrande,null);
    }

    public DateComparaisonException(GregorianCalendar datePetite) {
        this(datePetite,null);
    }

    public DateComparaisonException(String msg) {
        this.message = msg;
    }

    public DateComparaisonException() {
        this(null,null,"UNKNOWN");
    }

    public String getMessage(){
        String messageRetour="Une erreur est survenue avec la date";
        if(datePetite !=null){
            messageRetour+=": "+ datePetite;
        }
        messageRetour+=retourMessageErr();
        return messageRetour;
    }

    private String retourMessageErr(){
        String messageRetour="\n";
        if(message !=null){
            messageRetour+= message;
        }
        else{
            if (dateGrande != null){
                messageRetour=datePetite+" est plus grande que "+dateGrande;
            }
            else{
                if(datePetite != null){
                    messageRetour=datePetite+" est superieur à aujourd'hui";
                }
                else{
                    messageRetour="Erreur:inconnue";
                }
            }
        }
        return messageRetour;
    }
}
