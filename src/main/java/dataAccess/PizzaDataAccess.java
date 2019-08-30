package main.java.dataAccess;

import main.java.DAO.IPizzaDataAccess;
import main.java.exception.DataAccessException;
import main.java.factory.KnowIntFactory;
import main.java.model.ComponentPizza;
import main.java.model.Size;
import main.java.model.join.PizzaJoin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class PizzaDataAccess implements IPizzaDataAccess {

    public PizzaDataAccess() {
    }

    public ArrayList<PizzaJoin> searchPizza(Integer minPrice, Integer maxPrice, ArrayList<ComponentPizza> componentsList, Size size) throws DataAccessException {
        ArrayList<PizzaJoin> pizzaList = new ArrayList<>();
        try {
            HashMap<Integer,String> statementHashMap = new HashMap<>();
            int counterStatment = 0;
            String chaineWhere = " WHERE ";
            String sql = "SELECT " +
                    "piz.id as idPizza, " +
                    "piz.nom as nomPizza, " +
                    "pat_comp.nom as nomPate, "+
                    "sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix as prixTotal "+
                    "FROM pizza piz " +
                    "JOIN ingredient ingr on piz.id = ingr.idPizza " +
                    "JOIN taille tai on tai.id = piz.idTaille " +
                    "JOIN sauce sau on sau.idPizza = piz.id " +
                    "JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant " +
                    "JOIN composant sau_comp on sau_comp.id = sau.idComposant " +
                    "JOIN composant pat_comp on pat_comp.id = piz.idPate ";

            if(size.getId()!=999) {
                sql+=chaineWhere+" tai.id = ?  ";
                chaineWhere = " AND ";
                statementHashMap.put(counterStatment++,Integer.toString(size.getId()));
            }

            for (ComponentPizza componentPizza: componentsList) {
                sql+=chaineWhere;
                if(chaineWhere != " AND ") {
                    chaineWhere = " AND ";
                }
                sql+=" (pat_comp.nom = ? OR sau_comp.nom = ? OR ingr_comp.nom = ?) ";

                for (int i = 0; i < 3 ; i++) {

                    statementHashMap.put(counterStatment++,componentPizza.getName());
                }


            }
            sql+="GROUP BY piz.id,pat_comp.nom "+
                    "HAVING prixTotal < ? AND prixTotal > ?";
            statementHashMap.put(counterStatment++,Integer.toString(maxPrice));
            statementHashMap.put(counterStatment++,Integer.toString(minPrice));
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);

            for (int i=0; i < statementHashMap.size(); i++) {
                if(KnowIntFactory.isInt(statementHashMap.get(i))){
                    statement.setInt(i+1, Integer.parseInt(statementHashMap.get(i)));
                }else{
                    statement.setString(i+1, statementHashMap.get(i));
                }
            }

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                PizzaJoin pizzajoin = new PizzaJoin();
                pizzajoin.setId(rs.getInt("idPizza"));
                pizzajoin.setDoughName(rs.getString("nomPate"));
                pizzajoin.setName(rs.getString("nomPizza"));
                pizzaList.add(pizzajoin);
            }
            return pizzaList;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'optention de pizeza en base de donnée : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'optention de pizeza en base de donnée : "+e.getMessage());
        }
    }

    public ArrayList<PizzaJoin> searchOnePizza(Integer id) throws DataAccessException {
        ArrayList<PizzaJoin> pizzaList = new ArrayList<>();
        try {
            String sql = "SELECT " +
                    "piz.id as idPizza, " +
                    "piz.nom as nomPizza, " +
                    "sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix + sau_comp.prix as prixTotal, " +
                    "pat_comp.nom as nomPate, " +
                    "sau_comp.nom as nomSauce " +
                    "FROM pizza piz " +
                    "JOIN ingredient ingr on ingr.idPizza = piz.id " +
                    "JOIN taille tai on tai.id = piz.idTaille " +
                    "JOIN sauce sau on sau.idPizza = piz.id " +
                    "JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant " +
                    "JOIN composant sau_comp on sau_comp.id = sau.idComposant " +
                    "JOIN composant pat_comp on pat_comp.id = piz.idPate WHERE piz.id = ?";
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            walkInResultSet(rs,pizzaList);
            return pizzaList;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention d'une pizeza en BD : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention d'une pizeza en BD : "+e.getMessage());
        }
    }

    public ArrayList<PizzaJoin> listingPizzaForOrder() throws DataAccessException {
        ArrayList<PizzaJoin> pizzaList = new ArrayList<>();
        try {
            String sql = "SELECT " +
                    "piz.id as idPizza,"+
                    "piz.nom as nomPizza, " +
                    "sau_comp.nom as nomSauce, " +
                    "pat_comp.nom as nomPate, " +
                    "sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix + sau_comp.prix as prixTotal " +
                    "FROM pizza piz " +
                    "JOIN ingredient ingr on piz.id = ingr.idPizza " +
                    "JOIN taille tai on tai.id = piz.idTaille " +
                    "JOIN sauce sau on sau.idPizza = piz.id " +
                    "JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant " +
                    "JOIN composant sau_comp on sau_comp.id = sau.idComposant " +
                    "JOIN composant pat_comp on pat_comp.id = piz.idPate " +
                    "group by nomPizza,nomsauce,nomPate";
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            walkInResultSet(rs,pizzaList);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'obtention des pizzas pour les bons de commande :  "+e.getMessage());
        }


        return pizzaList;
    }


    public ArrayList<PizzaJoin> getPizzaFromPurchaseOrder(Integer id) throws DataAccessException {
        ArrayList<PizzaJoin> pizzaList = new ArrayList<>();
        try {
            String sql = "SELECT \n" +
                    "app.id as idAppartenance,\n" +
                    "piz.id as idPizza,\n" +
                    "piz.nom as nomPizza, \n" +
                    "sau_comp.nom as nomSauce, \n" +
                    "pat_comp.nom as nomPate, \n" +
                    "sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix + sau_comp.prix as prixTotal \n" +
                    "FROM appartenance app\n" +
                    "JOIN pizza piz on app.idPizza = piz.id\n" +
                    "JOIN ingredient ingr on piz.id = ingr.idPizza \n" +
                    "JOIN taille tai on tai.id = piz.idTaille \n" +
                    "JOIN sauce sau on sau.idPizza = piz.id \n" +
                    "JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant \n" +
                    "JOIN composant sau_comp on sau_comp.id = sau.idComposant \n" +
                    "JOIN composant pat_comp on pat_comp.id = piz.idPate \n" +
                    "WHERE app.idCommande = ?\n" +
                    "group by idAppartenance,nomPizza,nomsauce,nomPate;";
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            walkInResultSet(rs,pizzaList);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'obtention des pizzas pour les bons de commande :  "+e.getMessage());
        }


        return pizzaList;
    }

    public void walkInResultSet(ResultSet rs,ArrayList<PizzaJoin> pizzaList) throws DataAccessException {
        try {
            while (rs.next()) {
                PizzaJoin pizzajoin = new PizzaJoin();
                pizzajoin.setId(rs.getInt("idPizza"));
                pizzajoin.setName(rs.getString("nomPizza"));
                pizzajoin.setDoughName(rs.getString("nomPate"));
                pizzajoin.setSauceName(rs.getString("nomSauce"));
                pizzajoin.setPrice(rs.getDouble("prixTotal"));
                pizzaList.add(pizzajoin);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors du parcours des pizzas : " + e.getMessage());
        }
    }

    public ArrayList<ComponentPizza> searchComponentOfOnePizza(Integer id) throws DataAccessException {
        try {
            ArrayList<ComponentPizza> componentList = new ArrayList<>();
            String sql = "SELECT " +
                    "ingr_comp.id as id," +
                    "ingr_comp.nom as nom, " +
                    "ingr_comp.gout as gout, " +
                    "ingr_comp.prix as prix " +
                    "FROM pizza piz " +
                    "JOIN ingredient ingr on piz.id = ingr.idPizza " +
                    "JOIN taille tai on tai.id = piz.idTaille " +
                    "JOIN sauce sau on sau.idPizza = piz.id " +
                    "JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant " +
                    "JOIN composant sau_comp on sau_comp.id = sau.idComposant " +
                    "JOIN composant pat_comp on pat_comp.id = piz.idPate " +
                    "WHERE piz.id = ?";
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ComponentPizza component = new ComponentPizza();
                component.setId(rs.getInt("id"));
                component.setName(rs.getString("nom"));
                component.setTaste(rs.getString("gout"));
                component.setPrice(rs.getDouble("prix"));
                componentList.add(component);
            }
            return componentList;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'obtention des composants d'une pizza en base de donnée : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'obtention des composants d'une pizza en base de donnée : "+e.getMessage());
        }
    }

}
