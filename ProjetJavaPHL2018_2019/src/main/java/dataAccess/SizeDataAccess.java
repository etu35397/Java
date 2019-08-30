package main.java.dataAccess;

import main.java.DAO.ISizeDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.Size;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SizeDataAccess implements ISizeDataAccess {
    public SizeDataAccess() {
    }

    public ArrayList<Size> getSizeNameAndPrice() throws DataAccessException {
        try {
            ArrayList<Size> sizeList = new ArrayList<Size>();
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT * FROM taille";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                Size size = new Size();
                size.setId(rs.getInt("id"));
                size.setName(rs.getString("nom"));
                size.setPrice(rs.getDouble("prix"));
                sizeList.add(size);
            }
            return sizeList;
        }
        catch (SQLException ex){
            throw new DataAccessException("Probleme d'optention de l'id du nom ou du prix (SQL)");
        }
    }
}
