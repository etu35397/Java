package main.java.dataAccess;

import main.java.DAO.ITransformCitytoIdPizzeriaDataAccessFactory;
import main.java.exception.DataAccessException;
import main.java.model.join.PizzeriaCityJoin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransformCitytoIdPizzeriaDataAccessFactory implements ITransformCitytoIdPizzeriaDataAccessFactory {
    public Integer transform(PizzeriaCityJoin pizzeriaCityJoin) throws DataAccessException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "select piz.id from pizzeria piz " +
                    "JOIN localite loc on piz.idLocalite =  loc.id " +
                    "where loc.nom = ?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1,pizzeriaCityJoin.getName());
            ResultSet rs = statment.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException(" erreur lors de l'acces a la base de donnée localité " + e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException(" erreur lors de l'acces a la base de donnée localité " + e.getMessage());
        }
        return null;
    }
}
