package main.java.dataAccess;

import main.java.DAO.IComponentDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.ComponentPizza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComponentDataAccess implements IComponentDataAccess {
    public ComponentDataAccess() {
    }

    public ArrayList<ComponentPizza> getComponentNameAndPrice() throws DataAccessException {
        try {
            ArrayList<ComponentPizza> componentPizzaList = new ArrayList<>();
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT * FROM composant ORDER BY id";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                ComponentPizza componentPizza = new ComponentPizza();
                componentPizza.setName(rs.getString("nom"));
                componentPizza.setPrice(rs.getDouble("prix"));
                componentPizzaList.add(componentPizza);
            }
            return componentPizzaList;
        } catch (SQLException sql) {
            sql.printStackTrace();
            throw new DataAccessException("Impossible d'obtenir le nom et le prix des composants: "+ sql.getMessage());
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            throw new DataAccessException("Impossible d'obtenir le nom et le prix des composants: "+dae.getMessage());
        }
    }
}