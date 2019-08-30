package main.java.controller;

import main.java.business.MembershipBusiness;
import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.NameAndDateJoin;

import java.util.ArrayList;

public class MembershipController {
    private MembershipBusiness membershipBusiness;

    public MembershipController() {
        this.membershipBusiness = new MembershipBusiness();
    }

    public void groupCreate(MembershipJoin membershipJoin) throws DataAccessException, PurchaseOrderException, MembershipException {
        membershipBusiness.groupCreate(membershipJoin);
    }

    public void updateState(Integer id) throws DataAccessException {
        membershipBusiness.updateState(id);
    }

    public ArrayList<NameAndDateJoin> getPersonsFromPizzaId(Integer id) throws DataAccessException {
        return membershipBusiness.getPersonsFromPizzaId(id);
    }
}
