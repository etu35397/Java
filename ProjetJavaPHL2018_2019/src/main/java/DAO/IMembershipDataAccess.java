package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.exception.MembershipException;
import main.java.exception.PurchaseOrderException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.NameAndDateJoin;
import main.java.model.join.PizzaJoin;

import java.util.ArrayList;

public interface IMembershipDataAccess {

    //creer un purchase order et les tables appartenances qui y sont liés
    int create(MembershipJoin membershipJoin) throws DataAccessException, PurchaseOrderException, MembershipException;

    //creer une table appartenance
    int createOnlyOneTable(Integer idPurchaseOrder, PizzaJoin pizza) throws DataAccessException, MembershipException;

    //delete groupe appartenance
    int deleteLot(Integer id) throws DataAccessException;

    //pour la tache metier when clic bouton
    int updateState(int id) throws DataAccessException;

    // delete purchase order - delegation
    void deletePurchaseOrder(int id) throws DataAccessException;

    //delete appartenances et le bon
    void delete(int id) throws DataAccessException;

    ArrayList<NameAndDateJoin> getPersonsFromPizzaId(Integer id) throws DataAccessException;
}
