package main.java.dataAccess;

import main.java.DAO.ILocalityDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.Locality;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDataAccess implements ILocalityDataAccess {
    public LocalityDataAccess() {
    }

    public ArrayList<Locality> getAllLocality() throws DataAccessException {
        ArrayList<Locality> localityList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM localite ORDER BY nom";
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Locality locality = new Locality();
                locality.setId(rs.getInt("id"));
                locality.setCodePostal(rs.getInt("codePostal"));
                locality.setNom(rs.getString("nom"));
                localityList.add(locality);
            }
            return localityList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention des localités: "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention des localités: "+e.getMessage());

        }
    }

}
