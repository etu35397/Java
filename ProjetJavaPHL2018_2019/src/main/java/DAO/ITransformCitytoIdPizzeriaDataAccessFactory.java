package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.model.join.PizzeriaCityJoin;

public interface ITransformCitytoIdPizzeriaDataAccessFactory {

    Integer transform(PizzeriaCityJoin pizzeriaCityJoin) throws DataAccessException;
}
