package main.java.dataAccess;

import main.java.DAO.IPersonDataAccesDAO;
import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.FamillyMember;
import main.java.model.Holder;
import main.java.model.Person;
import main.java.model.join.ShowPersonReduLocalityJoin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDataAccess implements IPersonDataAccesDAO {

    public PersonDataAccess() {
    }

    public ArrayList<Person> getAll(boolean justIdNameLastName) throws DataAccessException, NumGsmException, DateComparaisonException {
        try {
            FamilyMemberDataAccess familyMemberDataAccess = new FamilyMemberDataAccess();
            HolderDataAccess holderDataAccess = new HolderDataAccess();

            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "select ";
            if (justIdNameLastName) {
                sql += "id, nom, prenom, typePersonne";
            } else {
                sql += "*";
            }

            sql += " from personne ORDER BY nom";
            PreparedStatement statment = connection.prepareStatement(sql);
            ResultSet rs = statment.executeQuery();

            ArrayList<Person> outPersonList = new ArrayList<Person>();
            while (rs.next()) {
                if (rs.getString("typePersonne") != "titulaire") {
                    outPersonList.add(holderDataAccess.getHolderForPersonDataAccess(rs, justIdNameLastName));
                } else {
                    outPersonList.add(familyMemberDataAccess.getFamilyMemberForPersonDataAccess(rs, justIdNameLastName));
                }

            }
            return outPersonList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces en base de donnée lors de l'optention de toutes les personnes :"+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces en base de donnée lors de l'optention de toutes les personnes :"+e.getMessage());
        } catch (NumGsmException e) {
            e.printStackTrace();
            throw new NumGsmException("erreur lors de l'optention du GSM lors de l'acces en base de donnée (optention de toutes les persones)"+e.getMessage());
        } catch (DateComparaisonException e) {
            e.printStackTrace();
            throw new DateComparaisonException("erreur date comparaison lors acces base de donnée lors de l'optention de toutes les personnes "+e.getMessage());
        }
    }

    public ShowPersonReduLocalityJoin getOneShowPersonReduLocality(int id) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("SELECT per.nom as nom," +
                            "per.prenom as prenom, " +
                            "per.estHomme as estHomme, " +
                            "IFNULL(per.rue, tit.rue) as rue, " +
                            "IFNULL(per.NumRue, tit.NumRue) as NumRue, " +
                            "IFNULL(per.numgsm, tit.numgsm) as numgsm, " +
                            "IFNULL(per.numtelephone, tit.numtelephone) as numtelephone, " +
                            "IFNULL(per.boite, tit.boite) as boite, " +
                            "IFNULL(loc.codePostal, loc_tit.codePostal) as codePostal, " +
                            "IFNULL(loc.nom, loc_tit.nom) as localite, " +
                            "IFNULL(per.nbpoint, tit.nbpoint) as nbpoint, " +
                            "per.typePersonne as typePers " +
                            "FROM Personne per " +
                            "LEFT JOIN localite loc ON loc.id = per.idLocalite " +
                            "LEFT JOIN personne tit ON tit.id = per.idTitulaire " +
                            "LEFT JOIN localite loc_tit ON loc_tit.id = tit.idLocalite " +
                            "where per.id = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

                if(rs.next()) {
                    ShowPersonReduLocalityJoin showPersonReduLocalityJoin = new ShowPersonReduLocalityJoin(
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("numGSM"),
                            rs.getBoolean("esthomme"),
                            rs.getString("rue"),
                            (Integer)rs.getInt("numrue"),
                            rs.getString("boite"),
                            rs.getString("numTelephone"),
                            (Integer)rs.getInt("nbpoint"),
                            (Integer)rs.getInt("codepostal"),
                            rs.getString("localite"),
                            rs.getString("typePers")
                    );

                    return showPersonReduLocalityJoin;
                }
                return null;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de la lecture ne base de donnée lors de l'optention jointure : localite personnes titulaire membrefamille");
        }
    }

    public ShowPersonReduLocalityJoin getOnePersonwithLocality(int id) throws DataAccessException {
        try {
            ShowPersonReduLocalityJoin showPersonReduLocalityJoin = new ShowPersonReduLocalityJoin();
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("SELECT per.nom as nom," +
                            "per.prenom as prenom, " +
                            "IFNULL(per.rue, tit.rue) as rue, " +
                            "IFNULL(per.NumRue, tit.NumRue) as NumRue, " +
                            "IFNULL(per.boite, tit.boite) as boite, " +
                            "IFNULL(loc.codePostal, loc_tit.codePostal) as codePostal, " +
                            "IFNULL(loc.nom, loc_tit.nom) as localite " +
                            "FROM Personne per " +
                            "LEFT JOIN localite loc ON loc.id = per.idLocalite " +
                            "LEFT JOIN personne tit ON tit.id = per.idTitulaire " +
                            "LEFT JOIN localite loc_tit ON loc_tit.id = tit.idLocalite " +
                            "where per.id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                showPersonReduLocalityJoin.setNom(rs.getString("nom"));
                showPersonReduLocalityJoin.setPrenom(rs.getString("prenom"));
                showPersonReduLocalityJoin.setRue(rs.getString("rue"));
                showPersonReduLocalityJoin.setNumRue(rs.getInt("numRue"));
                showPersonReduLocalityJoin.setBoite(rs.getString("boite"));
                showPersonReduLocalityJoin.setCodePostal(rs.getInt("codePostal"));
                showPersonReduLocalityJoin.setVille(rs.getString("localite"));
            }
                return showPersonReduLocalityJoin;
            }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces en base de donnée lors de l'optention de personnes+localités : "+ex.getMessage());
        }

    }

    public int delete(Person person) throws DataAccessException{

        if (person instanceof FamillyMember) {
            FamilyMemberDataAccess famillyMemberDataAccess = new FamilyMemberDataAccess();
            return famillyMemberDataAccess.delete((FamillyMember) person);
        } else if (person instanceof Holder) {
            HolderDataAccess holderDataAccess = new HolderDataAccess();
            return holderDataAccess.delete((Holder) person);
        }

        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("DELETE FROM personne WHERE id = ?");
            statement.setInt(1, person.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la suppression de la personne avec l'id " + person.getId());
        }
    }
}
