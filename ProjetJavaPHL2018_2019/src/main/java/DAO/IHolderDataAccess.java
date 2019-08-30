package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.Holder;

import java.sql.ResultSet;

public interface IHolderDataAccess {

    int delete(Holder holder) throws DataAccessException;

    Holder getHolderForPersonDataAccess(ResultSet rs, boolean justIdNameLastName) throws NumGsmException, DataAccessException, DateComparaisonException;

    Holder findOne(int idSearch) throws DataAccessException, DateComparaisonException, NumGsmException;
}
