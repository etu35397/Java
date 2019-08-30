package main.java.DAO;

import main.java.exception.DataAccessException;
import main.java.model.join.PizzaJoin;
import main.java.model.join.TicketJoin;

import java.util.ArrayList;

public interface ITicketDataAccess {

    TicketJoin getTicket(Integer id ) throws DataAccessException;

    ArrayList<PizzaJoin> getPizzaFromBD(Integer id) throws DataAccessException;

    void setAllAttributs(Integer id,TicketJoin ticketJoin) throws DataAccessException;
}
