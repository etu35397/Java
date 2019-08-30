package main.java.dataAccess;

import main.java.DAO.ITicketDataAccess;
import main.java.exception.DataAccessException;
import main.java.factory.DateFactory;
import main.java.model.join.PizzaJoin;
import main.java.model.join.TicketJoin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDataAccess implements ITicketDataAccess {
    public TicketDataAccess() {
    }

    public TicketJoin getTicket(Integer id ) throws DataAccessException {
        TicketJoin ticketJoin = new TicketJoin();
        ticketJoin.setPizzaList(getPizzaFromBD(id));
        this.setAllAttributs(id,ticketJoin);

        return ticketJoin;
    }

    public ArrayList<PizzaJoin> getPizzaFromBD(Integer id) throws DataAccessException {
        ArrayList<PizzaJoin> pizzaJoins= new ArrayList<>();
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("SELECT " +
                            "app.id as idAppartenance, " +
                            "piz.id as idPizza, " +
                            "piz.nom as nomPizza, " +
                            "sau_comp.nom as nomSauce, " +
                            "pat_comp.nom as nomPate, " +
                            "sum(ingr_comp.prix)/COUNT(DISTINCT sau_comp.id) + pat_comp.prix + tai.prix + sau_comp.prix as prixUnitaire " +
                            "FROM pizza piz " +
                            "JOIN ingredient ingr on piz.id = ingr.idPizza " +
                            "JOIN taille tai on tai.id = piz.idTaille " +
                            "JOIN sauce sau on sau.idPizza = piz.id " +
                            "JOIN composant ingr_comp on ingr_comp.id = ingr.idComposant " +
                            "JOIN composant sau_comp on sau_comp.id = sau.idComposant " +
                            "JOIN composant pat_comp on pat_comp.id = piz.idPate " +
                            "JOIN appartenance app on app.idPizza = piz.id " +
                            "JOIN commande bon on bon.id = app.idCommande " +
                            "WHERE bon.id = ? group by idAppartenance, nomPizza");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                PizzaJoin pizzaJoin = new PizzaJoin();
                pizzaJoin.setId(rs.getInt("idPizza"));
                pizzaJoin.setName(rs.getString("nomPizza"));
                pizzaJoin.setSauceName(rs.getString("nomSauce"));
                pizzaJoin.setDoughName(rs.getString("nomPate"));
                pizzaJoin.setPrice(rs.getDouble("prixUnitaire"));
                pizzaJoins.add(pizzaJoin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention des pizzas en base de donnée : " + e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention des pizzas en base de donnée : " + e.getMessage());
        }

        return pizzaJoins;
    }

    public void setAllAttributs(Integer id,TicketJoin ticketJoin) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("SELECT " +
                            "bon.nomReservation as nomReservation,  " +
                            "bon.dateCommande as dateCommande, " +
                            "bon.dateLivraison as dateLivraison, " +
                            "per.id as personneId, " +
                            "IFNULL(IFNULL(tit.nbPoint,per.nbPoint),-99999) as nbPoint, " +
                            "pzria.numRue as numRuePizzeria, " +
                            "pzria.rue as ruePizzeria, " +
                            "loc_piz.codePostal as codePostalPizzeria, " +
                            "loc_piz.nom as villePizzeria, " +
                            "pzria.boite as boitePizzeria, " +
                            "bon.numRue as numRueLivraison, " +
                            "bon.rue as rueLivraison, " +
                            "loc.codePostal as codePostalLivraison, " +
                            "loc.nom as villeLivraison, " +
                            "bon.boite as boiteLivraison " +
                            "FROM appartenance app " +
                            "left outer JOIN commande bon on bon.id = app.idCommande " +
                            "left outer JOIN pizzeria pzria on pzria.id = bon.idPizzeria  " +
                            "left outer JOIN localite loc on loc.id = bon.idLocalite " +
                            "left outer JOIN localite loc_piz on loc_piz.id = pzria.idLocalite " +
                            "left outer JOIN personne per on per.id = bon.idPersonne " +
                            "LEFT outer JOIN personne tit ON tit.id = per.idTitulaire " +
                            "WHERE bon.id = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                ticketJoin.setName(rs.getString("nomReservation"));
                ticketJoin.setOrderDate(DateFactory.sqlTimestampToGregorianCalendar(rs.getTimestamp("dateCommande")));
                ticketJoin.setDeliveryDate(DateFactory.sqlTimestampToGregorianCalendar(rs.getTimestamp("dateLivraison")));
                ticketJoin.setPersonId(rs.getInt("personneId"));
                ticketJoin.setPoint(rs.getInt("nbPoint"));
                ticketJoin.setNumRoadPizzeria(rs.getString("numRuePizzeria"));
                ticketJoin.setRoadPizzeria(rs.getString("ruePizzeria"));
                ticketJoin.setPostalCodePizzeria(rs.getInt("codePostalPizzeria"));
                ticketJoin.setCityPizzeria(rs.getString("villePizzeria"));
                ticketJoin.setBoxPizzeria(rs.getString("boitePizzeria"));
                ticketJoin.setNumRoadPerson(rs.getInt("numRueLivraison"));
                ticketJoin.setRoadPerson(rs.getString("rueLivraison"));
                ticketJoin.setPostalCodePerson(rs.getInt("codePostalLivraison"));
                ticketJoin.setCityPerson(rs.getString("villeLivraison"));
                ticketJoin.setBoxPerson(rs.getString("boiteLivraison"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

            throw new DataAccessException("Erreur lors de l'optention du ticket en base de donnée (jointure personne " +
                    "localite pizza composants pate taille sauce membreFamille) : " + e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("Erreur lors de l'optention du ticket en base de donnée (jointure personne " +
                    "localite pizza composants pate taille sauce membreFamille) : " + e.getMessage());
        }
    }


}
