package main.java.dataAccess;

import main.java.DAO.IHolderDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.factory.DateFactory;
import main.java.model.Holder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HolderDataAccess implements IHolderDataAccess {
    public HolderDataAccess(){
    }

    public int delete(Holder holder) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("DELETE FROM personne WHERE id = ?");
            statement.setInt(1, holder.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la suppression de la personne avec l'id " + holder.getId());
        }
    }

    public Holder getHolderForPersonDataAccess(ResultSet rs,boolean justIdNameLastName) throws NumGsmException, DataAccessException, DateComparaisonException {
        try {
            Holder holder = new Holder();
            holder.setNom(rs.getString("nom"));
            holder.setPrenom(rs.getString("prenom"));
            holder.setId(rs.getInt("id"));
            if (!justIdNameLastName) {

                holder.setDateNaissance(DateFactory.sqlDateToGregorianCalendar(rs.getDate("dateNaiss")));
                holder.setEstHomme(rs.getBoolean("esthomme"));
                if (rs.getString("numGSM") != null) {
                    holder.setNumGSM(rs.getString("numgsm"));
                }
                holder.setRue(rs.getString("rue"));
                holder.setNumRue(rs.getInt("numrue"));
                holder.setBoite(rs.getString("boite"));
                holder.setNumTel(rs.getString("numTelephone"));
                holder.setPoint(rs.getInt("nbPoint"));
                holder.setLocalite(rs.getInt("idLocalite"));
            }


            return holder;
        } catch (NumGsmException e) {
            e.printStackTrace();
            throw new NumGsmException("probleme lors de l'optention du gsm du titulaire"+e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("probleme lié a la base de donnée lors de l'acces aux titulaires: "+e.getMessage());
        } catch (DateComparaisonException e) {
            e.printStackTrace();
            throw new DateComparaisonException("probleme lié a la date lors de l'optention d'un titulaire en base de donnée: "+e.getMessage());
        }

    }

    public Holder findOne(int idSearch) throws DataAccessException, DateComparaisonException, NumGsmException {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            String sql = "select * from personne where id = ?";
            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setInt(1, idSearch);
            ResultSet rs = statment.executeQuery();
            if (rs.next()) {
                return new Holder(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("numGSM"),
                        DateFactory.sqlDateToGregorianCalendar(rs.getDate("dateNaiss")),
                        rs.getBoolean("estHomme"),
                        rs.getString("typePersonne"),
                        rs.getString("rue"),
                        rs.getInt("numRue"),
                        rs.getString("boite"),
                        rs.getString("numTelephone"),
                        rs.getInt("nbPoint"),
                        rs.getInt("idLocalite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("erreur lors de l'acces a la base de donnée pour obtenir une personne"+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (DateComparaisonException e) {
            e.printStackTrace();
            throw new DateComparaisonException("probleme lié a la date lors de l'optention d'une personne en base de donnée: "+e.getMessage());
        } catch (NumGsmException e) {

            e.printStackTrace();
            throw new NumGsmException("erreur sur le numero de GSM lors de l'optention d'une personne en base de donnée");
        }
        return null;
    }

}
