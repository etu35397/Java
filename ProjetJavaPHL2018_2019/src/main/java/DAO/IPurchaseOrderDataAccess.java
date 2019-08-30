package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.PurchaseOrderJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface IPurchaseOrderDataAccess {

    ArrayList<PurchaseOrderJoin> getAllPuschaseOrder() throws DataAccessException, PurchaseOrderException;

    ArrayList<PurchaseOrderJoin> getPuchaseOrderListing(GregorianCalendar firstDate, GregorianCalendar lastDate, boolean isPaid) throws DataAccessException;

    //creer un bon de commande sans liens
    int createOne(MembershipJoin purchaseOrder) throws DataAccessException;

    //delete un bon brute
    int deleteOne(Integer id) throws DataAccessException;

    // update 1 bon de commande
    int updateOnlyOne(MembershipJoin purchaseOrder) throws DataAccessException;

    //update un bon de commande avec liens appartenance
    void update(MembershipJoin membershipJoin) throws DataAccessException, MembershipException;


    //get le nouvel ID
    int getMaxId() throws DataAccessException;
}
