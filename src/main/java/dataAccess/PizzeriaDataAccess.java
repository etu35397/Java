package main.java.dataAccess;

import main.java.DAO.IPizzeriaDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.PizzeriaException;
import main.java.factory.DateFactory;
import main.java.model.join.PizzeriaAdressJoin;
import main.java.model.join.PizzeriaCityJoin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PizzeriaDataAccess implements IPizzeriaDataAccess {
    public ArrayList<PizzeriaCityJoin> getAllTownPizzeria() throws DataAccessException {
        try {
            ArrayList<PizzeriaCityJoin> pizzeriaList = new ArrayList<>();
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT " +
                    "loc.id as id, "+
                    "loc.nom as ville " +
                    "FROM pizzeria piz " +
                    "JOIN localite loc on loc.id = piz.idLocalite";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();
            while (rs.next()) {
                PizzeriaCityJoin pizzeria = new PizzeriaCityJoin();
                pizzeria.setId(rs.getInt("id"));
                pizzeria.setName(rs.getString("ville"));
                pizzeriaList.add(pizzeria);
            }
            return pizzeriaList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'obtention des villes des pizzerias : " + e.getMessage());
        }
    }

    public ArrayList<PizzeriaAdressJoin> getAdressPizzeria(Integer id) throws PizzeriaException, DataAccessException {
        try {
            ArrayList<PizzeriaAdressJoin> pizzeriaAdrList = new ArrayList<>();
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT " +
                    "piz.id as id, " +
                    "piz.rue as rue, " +
                    "piz.numRue as numRue, " +
                    "piz.boite as boite " +
                    "FROM pizzeria piz " +
                    "JOIN localite loc on loc.id = piz.idLocalite " +
                    "where loc.id = ? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PizzeriaAdressJoin pizzeriaAdr = new PizzeriaAdressJoin();
                pizzeriaAdr.setId(rs.getInt("id"));
                pizzeriaAdr.setRoad(rs.getString("rue"));
                pizzeriaAdr.setNumRoad(rs.getString("numRue"));
                pizzeriaAdr.setBoite(rs.getString("boite"));
                pizzeriaAdrList.add(pizzeriaAdr);
            }
            return pizzeriaAdrList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'ardess complete des pizzerias");
        }
    }

    public ArrayList<PizzeriaAdressJoin> getAllPizzeria() throws PizzeriaException, DataAccessException {
        try {
            ArrayList<PizzeriaAdressJoin> pizzeriaAdrList = new ArrayList<>();
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT " +
                    "piz.id as id, " +
                    "piz.rue as rue, " +
                    "piz.numRue as numRue, " +
                    "piz.boite as boite " +
                    "FROM pizzeria piz ";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PizzeriaAdressJoin pizzeriaAdr = new PizzeriaAdressJoin();
                pizzeriaAdr.setId(rs.getInt("id"));
                pizzeriaAdr.setRoad(rs.getString("rue"));
                pizzeriaAdr.setNumRoad(rs.getString("numRue"));
                pizzeriaAdr.setBoite(rs.getString("boite"));
                pizzeriaAdrList.add(pizzeriaAdr);
            }
            return pizzeriaAdrList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'ardess complete des pizzerias");
        }
    }

    public Double getAveragePrice(Integer idPizzeria, GregorianCalendar dateDebut, GregorianCalendar dateFin) throws DataAccessException {
        try {
            ArrayList<PizzeriaAdressJoin> pizzeriaAdrList = new ArrayList<>();
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT AVG(app.prix) as price\n" +
                    "FROM pizzeria pizr\n" +
                    "JOIN commande com on com.idPizzeria = pizr.id\n" +
                    "JOIN appartenance app on app.idCommande = com.id\n" +
                    "WHERE pizr.id = ? " +
                    "AND com.dateCommande >= ? " +
                    "AND com.dateCommande <= ? " +
                    "AND com.estPaye = 1 ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPizzeria);
            statement.setTimestamp(2, DateFactory.gregorianCalendarToSqlTimeStamp(dateDebut));
            statement.setTimestamp(3, DateFactory.gregorianCalendarToSqlTimeStamp(dateFin));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getDouble("price");
            }
            return 0.00;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'ardess complete des pizzerias");
        }
    }
}
