package main.java.business;

import main.java.DAO.IMembershipDataAccess;
import main.java.DAO.IPurchaseOrderDataAccess;
import main.java.dataAccess.MembershipDataAccess;
import main.java.dataAccess.PurchaseOrderDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.PurchaseOrderJoin;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PurchaseOrderBusiness {

    private IPurchaseOrderDataAccess purchaseOrderDataAccess;
    private IMembershipDataAccess membershipDataAccess;

    public PurchaseOrderBusiness() {
        this.purchaseOrderDataAccess = new PurchaseOrderDataAccess();
        this.membershipDataAccess = new MembershipDataAccess();
    }

    public ArrayList<PurchaseOrderJoin> listing(GregorianCalendar dateDebut, GregorianCalendar dateFin, Boolean estPaye) throws DataAccessException {
        return purchaseOrderDataAccess.getPuchaseOrderListing(dateDebut, dateFin, estPaye);
    }

    public ArrayList<PurchaseOrderJoin> getAllPuschaseOrder() throws DataAccessException, PurchaseOrderException {
        return purchaseOrderDataAccess.getAllPuschaseOrder();
    }

    public int create(MembershipJoin purchaseOrder) throws DataAccessException, PurchaseOrderException, MembershipException {
        return membershipDataAccess.create(purchaseOrder);
    }

    public void update(MembershipJoin membershipJoin) throws DataAccessException, MembershipException {
        purchaseOrderDataAccess.update(membershipJoin);
    }

    public void delete(int id) throws DataAccessException {
        membershipDataAccess.delete(id);
    }
}
