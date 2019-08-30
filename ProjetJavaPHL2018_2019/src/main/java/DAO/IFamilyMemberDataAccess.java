package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.FamillyMember;

import java.sql.ResultSet;

public interface IFamilyMemberDataAccess {

    FamillyMember getFamilyMemberForPersonDataAccess(ResultSet rs, boolean justIdNameLastName) throws DataAccessException, NumGsmException, DateComparaisonException;

    int delete(FamillyMember membreFamille) throws DataAccessException;
}
