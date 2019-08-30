package main.java.dataAccess;

import main.java.DAO.IMembershipDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.factory.DateFactory;
import main.java.model.ComponentPizza;
import main.java.model.join.MembershipJoin;
import main.java.model.join.NameAndDateJoin;
import main.java.model.join.PizzaJoin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipDataAccess implements IMembershipDataAccess {
    public MembershipDataAccess() {
    }

    //creer un purchase order et les tables appartenances qui y sont liés
    public int create(MembershipJoin membershipJoin) throws DataAccessException, PurchaseOrderException, MembershipException {
        try{
            TransformCitytoIdPizzeriaDataAccessFactory transformCitytoIdPizzeriaDataAccessFactory = new TransformCitytoIdPizzeriaDataAccessFactory();
            membershipJoin.setPizzeria(transformCitytoIdPizzeriaDataAccessFactory.transform(membershipJoin.getCityPizzeria()));
            PurchaseOrderDataAccess purchaseOrderDataAccess = new PurchaseOrderDataAccess();
            purchaseOrderDataAccess.createOne(membershipJoin);
            Integer idMax = purchaseOrderDataAccess.getMaxId();
            for (PizzaJoin pizza: membershipJoin.getPizzaList() ) {
                this.createOnlyOneTable(idMax,pizza);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la création d'un bon de commande et des tables appartenances liées"+e.getMessage());
        } catch (MembershipException e) {
            e.printStackTrace();
            throw new MembershipException("erreur creation de la table appartenance: "+e.getMessage());
        }
        return -1;
    }

    //creer une table appartenance
    public int createOnlyOneTable(Integer idPurchaseOrder, PizzaJoin pizza) throws DataAccessException, MembershipException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("INSERT INTO Appartenance (idPizza, idCommande, prix) VALUES (?, ?, ?)");
            statement.setInt(1, pizza.getId());
            statement.setInt(2, idPurchaseOrder);
            statement.setDouble(3, pizza.getPrice());
            return statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces en base de donnée pour l'insertion d'une seul appartenance(sql)"+e.getMessage());
        }
        catch(DataAccessException ex){
            ex.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces en base de donnée pour l'insertion d'une seul appartenance"+ ex.getMessage());
        }
    }

    //delete groupe appartenance
    public int deleteLot(Integer id) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("DELETE FROM appartenance WHERE idCommande = ?");
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("(sql) Erreur lors de l'acces en base de donnée lors de la suppression du bon avec l'id " + id+" : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'acces en base de donnée lors de la suppression du bon avec l'id " + id+" : "+e.getMessage() );

        }
    }

    //pour la tache metier when clic bouton
    public int updateState(int id) throws DataAccessException{
        try{
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE commande SET estPaye = true WHERE id = ?"
            );
            statement.setInt(1,id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces en base de donnée : mise a jour de l'etat : "+e.getMessage());
        }
    }

    // delete purchase order - delegation
    public void deletePurchaseOrder(int id) throws DataAccessException {
        try{
            PurchaseOrderDataAccess purchaseOrderDataAccess = new PurchaseOrderDataAccess();
            purchaseOrderDataAccess.deleteOne(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la suppression des bons de commande"+e.getMessage());
        }
    }

    //delete appartenances et le bon
    public void delete(int id) throws DataAccessException {
        this.deleteLot(id);
        this.deletePurchaseOrder(id);
    }

    public ArrayList<NameAndDateJoin> getPersonsFromPizzaId(Integer id) throws DataAccessException {
        try {
            ArrayList<NameAndDateJoin> customers = new ArrayList<>();
            String sql = "SELECT\n" +
                    "pers.nom as nom,\n" +
                    "pers.prenom as prenom,\n" +
                    "com.dateCommande as dateCommande\n" +
                    "FROM pizza pizz\n" +
                    "JOIN appartenance app on app.idPizza = pizz.id\n" +
                    "JOIN commande com on com.id = app.idCommande\n" +
                    "JOIN personne pers on com.idPersonne = pers.id\n" +
                    "WHERE pizz.id = ?;";
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NameAndDateJoin customer = new NameAndDateJoin();
                customer.setNom(rs.getString("nom"));
                customer.setPrénom(rs.getString("prenom"));
                customer.setDateCommande(DateFactory.sqlTimestampToGregorianCalendar(rs.getTimestamp("dateCommande")));
                customers.add(customer);
            }
            return customers;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'obtention des composants d'une pizza en base de donnée : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'obtention des composants d'une pizza en base de donnée : "+e.getMessage());
        }
    }

}
