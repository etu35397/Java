package main.java.dataAccess;

import main.java.DAO.IPurchaseOrderDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.factory.DateFactory;
import main.java.model.join.MembershipJoin;
import main.java.model.join.PizzaJoin;
import main.java.model.join.PurchaseOrderJoin;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class PurchaseOrderDataAccess implements IPurchaseOrderDataAccess {
    public PurchaseOrderDataAccess()  {
    }

    public ArrayList<PurchaseOrderJoin> getAllPuschaseOrder() throws DataAccessException, PurchaseOrderException {
        try {
            ArrayList<PurchaseOrderJoin> purchaseOrderJoinList = new ArrayList<>();
            Connection connection = DatabaseConnection.getInstance().getConnection();



            String sql = "SELECT " +
                    "com.id as NumeroCommande, " +
                    "com.nomReservation as nomReservation, " +
                    "com.dateCommande as dateCommande, " +
                    "com.dateLivraison as dateLivraison, " +
                    "com.rue as rueLivraison, " +
                    "com.numRue as numRueLivraison, " +
                    "com.boite as boiteLivraison, " +
                    "com.estPaye as estPaye, "+
                    "loc_cli.codePostal as codePostalLivraison, " +
                    "loc_cli.nom as villeLivraison, " +
                    "piz.rue as ruePizzeria, " +
                    "piz.numRue as numRuePizzeria, " +
                    "loc_piz.codePostal as codePostalPizzeria, " +
                    "loc_piz.nom as villePizzeria " +
                    "FROM commande com " +
                    "LEFT JOIN localite loc_cli on loc_cli.id = com.idLocalite " +
                    "LEFT JOIN pizzeria piz on piz.id = com.idPizzeria " +
                    "LEFT JOIN localite loc_piz on loc_piz.id = piz.idLocalite " +
                    "ORDER BY estPaye,dateLivraison ASC";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PurchaseOrderJoin purchaseOrder = new PurchaseOrderJoin();
                purchaseOrder.setId(rs.getInt("numeroCommande"));
                purchaseOrder.setNomReservation(rs.getString("nomReservation"));

                Timestamp timestamp = rs.getTimestamp("dateCommande");
                if (timestamp != null) {
                    Date dateCommande = new Date(timestamp.getTime());
                    purchaseOrder.setDateCommande(DateFactory.dateToGregorianCalendar(dateCommande));
                }


                timestamp = rs.getTimestamp("dateLivraison");
                if (timestamp != null) {
                    Date dateLivraison = new Date(timestamp.getTime());
                    purchaseOrder.setDateLivraison(DateFactory.dateToGregorianCalendar(dateLivraison));
                }

                purchaseOrder.setRueLivraison(rs.getString("rueLivraison"));
                purchaseOrder.setNumRueLivraison(rs.getString("numRueLivraison"));
                purchaseOrder.setBoiteLivraison(rs.getString("boiteLivraison"));
                purchaseOrder.setCodePostalLivraison(rs.getInt("codePostalLivraison"));
                purchaseOrder.setVilleLivraison(rs.getString("villeLivraison"));
                purchaseOrder.setRuePizzeria(rs.getString("ruePizzeria"));
                purchaseOrder.setNumRuePizzeria(rs.getString("numRuePizzeria"));
                purchaseOrder.setCodePostalPizzeria(rs.getInt("codePostalPizzeria"));
                purchaseOrder.setVillePizzeria(rs.getString("villePizzeria"));
                purchaseOrder.setPaid(rs.getBoolean("estPaye"));
                purchaseOrderJoinList.add(purchaseOrder);
            }

            return purchaseOrderJoinList;
        }
        catch(SQLException e){
        e.printStackTrace();
        throw new DataAccessException("erreur lors de l'obtention de tous les bon de commandes en base de donnée: "+e.getMessage());
    }
}

    public ArrayList<PurchaseOrderJoin> getPuchaseOrderListing(GregorianCalendar firstDate, GregorianCalendar lastDate,boolean isPaid) throws DataAccessException {
        try{
        ArrayList<PurchaseOrderJoin> purchaseOrderJoinList = new ArrayList<>();
        Connection connection = DatabaseConnection.getInstance().getConnection();



        String sql = "SELECT " +
                "com.id as NumeroCommande, " +
                "com.estPaye as estPaye, "+
                "com.nomReservation as nomReservation, " +
                "com.dateCommande as dateCommande, " +
                "com.dateLivraison as dateLivraison, " +
                "com.rue as rueLivraison, " +
                "com.numRue as numRueLivraison, " +
                "com.boite as boiteLivraison, " +
                "loc_cli.codePostal as codePostalLivraison, " +
                "loc_cli.nom as villeLivraison, " +
                "piz.rue as ruePizzeria, " +
                "piz.numRue as numRuePizzeria, " +
                "loc_piz.codePostal as codePostalPizzeria, " +
                "loc_piz.nom as villePizzeria " +
                "FROM commande com " +
                "LEFT JOIN localite loc_cli on loc_cli.id = com.idLocalite " +
                "LEFT JOIN pizzeria piz on piz.id = com.idPizzeria " +
                "LEFT JOIN localite loc_piz on loc_piz.id = piz.idLocalite " +
                "WHERE  com.dateCommande >= ? AND com.dateCommande <= ? AND com.estPaye = ? "+
                "ORDER BY com.dateCommande ASC";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setTimestamp(1, DateFactory.gregorianCalendarToSqlTimeStamp(firstDate));
        statement.setTimestamp(2, DateFactory.gregorianCalendarToSqlTimeStamp(lastDate));
        statement.setBoolean(3, isPaid);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            PurchaseOrderJoin purchaseOrder = new PurchaseOrderJoin();
            purchaseOrder.setId(rs.getInt("numeroCommande"));
            purchaseOrder.setNomReservation(rs.getString("nomReservation"));

            Timestamp timestamp = rs.getTimestamp("dateCommande");
            if (timestamp != null) {
                Date dateCommande = new Date(timestamp.getTime());
                purchaseOrder.setDateCommande(DateFactory.dateToGregorianCalendar(dateCommande));
            }


            timestamp = rs.getTimestamp("dateLivraison");
            if (timestamp != null) {
                Date dateLivraison = new Date(timestamp.getTime());
                purchaseOrder.setDateLivraison(DateFactory.dateToGregorianCalendar(dateLivraison));
            }

            purchaseOrder.setRueLivraison(rs.getString("rueLivraison"));
            purchaseOrder.setNumRueLivraison(rs.getString("numRueLivraison"));
            purchaseOrder.setBoiteLivraison(rs.getString("boiteLivraison"));
            purchaseOrder.setCodePostalLivraison(rs.getInt("codePostalLivraison"));
            purchaseOrder.setVilleLivraison(rs.getString("villeLivraison"));
            purchaseOrder.setRuePizzeria(rs.getString("ruePizzeria"));
            purchaseOrder.setNumRuePizzeria(rs.getString("numRuePizzeria"));
            purchaseOrder.setCodePostalPizzeria(rs.getInt("codePostalPizzeria"));
            purchaseOrder.setVillePizzeria(rs.getString("villePizzeria"));
            purchaseOrder.setPaid(rs.getBoolean("estPaye"));
            purchaseOrderJoinList.add(purchaseOrder);
        }
        return purchaseOrderJoinList;
    } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la lecture des bons de commande en base de donnée");
        }
    }

    //creer un bon de commande sans liens
    public int createOne(MembershipJoin purchaseOrder) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("INSERT INTO Commande (id, nomReservation, dateCommande, dateLivraison, rue, numRue, boite, estPaye, idLocalite, idPersonne, idPizzeria) VALUES " +
                            "(?,?,?,?,?,?,?,?,?,?,?)");
            statement.setNull(1, Types.INTEGER);
            statement.setString(2, purchaseOrder.getNomReservation());
            statement.setTimestamp(3, DateFactory.gregorianCalendarToSqlTimeStamp(purchaseOrder.getDateCommande()));
            statement.setTimestamp(4, DateFactory.gregorianCalendarToSqlTimeStamp(purchaseOrder.getDateLivraison()));
            statement.setString(5, purchaseOrder.getRue());
            statement.setString(6, purchaseOrder.getNumRue());
            if(purchaseOrder.getBoite()==null) {statement.setNull(7, Types.VARCHAR);}
            else {statement.setString(7, purchaseOrder.getBoite());}
            statement.setBoolean(8, purchaseOrder.getEstPaye());
            statement.setInt(9, purchaseOrder.getLocalite());
            if(purchaseOrder.getPersonne()==null){statement.setNull(10, Types.INTEGER);}
            else statement.setInt(10, purchaseOrder.getPersonne());
            statement.setInt(11, purchaseOrder.getPizzeria());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la creation d'un bon de commande : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la creation d'un bon de commande : "+e.getMessage());
        }
    }

    //delete un bon brute
    public int deleteOne(Integer id) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("DELETE FROM commande WHERE id = ?");
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la suppression du bon avec l'id " + id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de la suppression du bon avec l'id " + id);

        }
    }

    // update 1 bon de commande
    public int updateOnlyOne(MembershipJoin purchaseOrder) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("UPDATE commande " +
                            "SET nomReservation = ? , " +
                            "dateCommande = ?, " +
                            "dateLivraison = ?, " +
                            "rue = ?, " +
                            "numRue = ?, " +
                            "boite = ?, " +
                            "estPaye = ?, " +
                            "idLocalite = ?, " +
                            "idPersonne = ?, " +
                            "idPizzeria = ? " +
                            "WHERE id = ?;");

            statement.setString(1, purchaseOrder.getNomReservation());
            statement.setTimestamp(2, DateFactory.gregorianCalendarToSqlTimeStamp(purchaseOrder.getDateCommande()));
            statement.setTimestamp(3, DateFactory.gregorianCalendarToSqlTimeStamp(purchaseOrder.getDateLivraison()));
            statement.setString(4, purchaseOrder.getRue());
            statement.setString(5, purchaseOrder.getNumRue());
            if(purchaseOrder.getBoite()==null)statement.setNull(6, Types.VARCHAR);
            else statement.setString(6, purchaseOrder.getBoite());
            statement.setBoolean(7, purchaseOrder.getEstPaye());
            statement.setInt(8, purchaseOrder.getLocalite());
            if(purchaseOrder.getPersonne()==null)statement.setNull(9, Types.INTEGER);
            else statement.setInt(9, purchaseOrder.getPersonne());
            statement.setInt(10, purchaseOrder.getPizzeria());
            statement.setInt(11, purchaseOrder.getId());


            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la mise a jour d'un bon de commande : "+e.getMessage());
        }
    }

    //update un bon de commande avec liens appartenance
    public void update(MembershipJoin membershipJoin) throws DataAccessException, MembershipException {
        try{
            // 1. Update la commande (adresse de livraison, nom reservation, etc)
            this.updateOnlyOne(membershipJoin);

            // 2. On delete toutes les pizzas de la commande (dans la table appartenance)
            MembershipDataAccess membershipDataAccess = new MembershipDataAccess();
            membershipDataAccess.deleteLot(membershipJoin.getId());

            // 3. On recrée les pizzas dans la table appartenance
            for(PizzaJoin pizza : membershipJoin.getPizzaList()){
                membershipDataAccess.createOnlyOneTable(membershipJoin.getId(),pizza);
            }

        } catch (DataAccessException e) {

            e.printStackTrace();
            throw new DataAccessException("erreur lors de la mise a jour Complete des bons de commandes en BD : "+e.getMessage());
        } catch (MembershipException e) {
            e.printStackTrace();
            throw new MembershipException("erreur lors de la mise a jour MemberShip : "+e.getMessage());
        }
    }

    //get le nouvel ID
    public int getMaxId() throws DataAccessException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();

            String sql = "SELECT MAX(id) as maxPlusUn FROM commande";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                return rs.getInt("maxPlusUn");
            }

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();

            throw new DataAccessException("erreur lors de l'obtention du dernier ID du bon de commande : " + e.getMessage());
        }
    }

}
