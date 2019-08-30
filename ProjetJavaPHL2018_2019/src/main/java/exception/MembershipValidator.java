package main.java.exception;

import main.java.model.join.MembershipJoin;

import java.util.GregorianCalendar;

public class MembershipValidator {

    public static boolean validate(MembershipJoin membershipJoin) throws MembershipValidatorException {

        if (membershipJoin == null) {
            throw new MembershipValidatorException("Le membershipJoin ne peut pas être à null" );
        }

        GregorianCalendar dateCommande = new GregorianCalendar();
        dateCommande.setTime(membershipJoin.getDateCommande().getTime());
        GregorianCalendar dateLivraison = new GregorianCalendar();
        dateLivraison.setTime(membershipJoin.getDateLivraison().getTime());

        if (dateCommande.compareTo(dateLivraison) > 0) {
            throw new MembershipValidatorException("La date de livraison ne peut pas être inférieur à celle de commande");
        }

        if(membershipJoin.getNomReservation().equals("") || membershipJoin.getNomReservation().length() > 255) {
            throw new MembershipValidatorException("Le nom de réservation ne peut être supérieur à 255 caractères ou être vide");
        }

        if(membershipJoin.getRue().length() > 255) {
            throw new MembershipValidatorException("Le nom de la rue ne peut être supérieur à 255 caractères");
        }

        if (membershipJoin.getBoite() != null && membershipJoin.getBoite().length() > 4) {
            throw new MembershipValidatorException("La boite ne peut pas avoir plus de 4 caractères");
        }

        if(membershipJoin.getNumRue().length() > 4) {
            throw new MembershipValidatorException("Le numéro de rue ne peut pas avoir plus de 4 caractères");
        }

        if(membershipJoin.getRuePizzeria().length() > 255) {
            throw new MembershipValidatorException("Le nom de la rue ne peut être supérieur à 255 caractères");
        }

        if (membershipJoin.getBoitePizzeria() != null && membershipJoin.getBoitePizzeria().length() > 4) {
            throw new MembershipValidatorException("La boite ne peut pas avoir plus de 4 caractères");
        }

        if(membershipJoin.getNumRuePizzeria().length() > 4) {
            throw new MembershipValidatorException("Le numéro de rue ne peut pas avoir plus de 4 caractères");
        }

        if(membershipJoin.getPizzaList().isEmpty()) {
            throw new MembershipValidatorException("La liste de pizza(s) commandée(s) ne peut être vide");
        }

        return true;
    }
}


