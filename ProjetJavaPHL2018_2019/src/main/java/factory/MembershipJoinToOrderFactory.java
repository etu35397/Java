package main.java.factory;

import main.java.model.Order;
import main.java.model.join.MembershipJoin;

public class MembershipJoinToOrderFactory {
    public static Order transorm(MembershipJoin membershipJoin){
        Order outOrder = new Order();
        outOrder.setNomReservation(membershipJoin.getNomReservation());
        outOrder.setDateCommande(membershipJoin.getDateCommande());
        outOrder.setDateLivraison(membershipJoin.getDateLivraison());
        outOrder.setNumRue(membershipJoin.getNumRue());
        outOrder.setBoite(membershipJoin.getBoite());
        outOrder.setEstPaye(membershipJoin.getEstPaye());
        outOrder.setLocalite(membershipJoin.getLocalite());
        outOrder.setPersonne(membershipJoin.getPersonne());
        outOrder.setPizzeria(membershipJoin.getPizzeria());
        return outOrder;
    }
}
