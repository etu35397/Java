package main.java.business;

import main.java.DAO.IMembershipDataAccess;
import main.java.dataAccess.MembershipDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.NameAndDateJoin;

import java.util.ArrayList;

public class MembershipBusiness {
    private IMembershipDataAccess membershipDataAccess;

    public MembershipBusiness() {
        setMembershipDataAccess(new MembershipDataAccess());
    }

    public void setMembershipDataAccess(MembershipDataAccess membershipDataAccess) {
        this.membershipDataAccess = membershipDataAccess;
    }

    public void updateState(Integer id) throws DataAccessException {
        membershipDataAccess.updateState(id);
    }

    public void groupCreate(MembershipJoin membershipJoin) throws DataAccessException, PurchaseOrderException, MembershipException {
        membershipDataAccess.create(membershipJoin);
    }

    public ArrayList<NameAndDateJoin> getPersonsFromPizzaId(Integer id) throws DataAccessException {
        return membershipDataAccess.getPersonsFromPizzaId(id);
    }
}
