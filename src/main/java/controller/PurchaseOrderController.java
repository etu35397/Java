package main.java.controller;

import main.java.business.PurchaseOrderBusiness;
import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.PurchaseOrderJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PurchaseOrderController {
    PurchaseOrderBusiness purchaseOrderBusiness;

    public PurchaseOrderController() {this.purchaseOrderBusiness = new PurchaseOrderBusiness();}

    public ArrayList<PurchaseOrderJoin> getPurchaseOrderListing(GregorianCalendar dateDebut, GregorianCalendar dateFin, Boolean estPaye) throws DataAccessException {
        return purchaseOrderBusiness.listing(dateDebut, dateFin, estPaye);
    }

    public ArrayList<PurchaseOrderJoin> getAllPuschaseOrder() throws DataAccessException, PurchaseOrderException {
        return purchaseOrderBusiness.getAllPuschaseOrder();
    }

    public int create(MembershipJoin purchaseOrder) throws PurchaseOrderException, DataAccessException, MembershipException {
        return purchaseOrderBusiness.create(purchaseOrder);
    }

    public void update(MembershipJoin membershipJoin) throws DataAccessException, MembershipException {
        purchaseOrderBusiness.update(membershipJoin);
    }

    public void delete(int id) throws DataAccessException {
        purchaseOrderBusiness.delete(id);
    }
}
