package main.java.dataAccess;

import main.java.DAO.IFamilyMemberDataAccess;
import main.java.exception.DataAccessException;
import main.java.exception.DateComparaisonException;
import main.java.exception.NumGsmException;
import main.java.model.FamillyMember;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMemberDataAccess implements IFamilyMemberDataAccess {
    public FamilyMemberDataAccess() {
    }

    public FamillyMember getFamilyMemberForPersonDataAccess(ResultSet rs,boolean justIdNameLastName) throws DataAccessException, NumGsmException, DateComparaisonException {
        try {
            FamillyMember familyMember = new FamillyMember();
            familyMember.setNom(rs.getString("nom"));
            familyMember.setPrenom(rs.getString("prenom"));
            familyMember.setId(rs.getInt("id"));
            if (!justIdNameLastName) {

                if (rs.getString("numGSM") != null) {
                    familyMember.setNumGSM(rs.getString("numgsm"));
                }
                familyMember.setTitulaire(rs.getInt("idTit ulaire"));
            }
            return familyMember;
        } catch (NumGsmException e) {
            e.printStackTrace();
            throw new NumGsmException("probleme num gsm exception"+e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("probleme lors de l'acces en base de donnée : obtention d'un membre famille : "+e.getMessage());
        } catch (DateComparaisonException e) {
            e.printStackTrace();
            throw new DateComparaisonException("probleme Date Comparaison : "+e.getMessage());
        }
    }


    public int delete(FamillyMember membreFamille) throws DataAccessException {
        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection()
                    .prepareStatement("DELETE FROM personne WHERE id = ?");
            statement.setInt(1, membreFamille.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("probleme lors de l'acces en base de donnée dans la suppression d'un client : "+e.getMessage());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DataAccessException("probleme lors de la suppression d'un client : "+e.getMessage());
        }
    }
}
