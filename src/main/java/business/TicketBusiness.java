package main.java.business;

import main.java.DAO.ITicketDataAccess;
import main.java.dataAccess.TicketDataAccess;
import main.java.exception.DataAccessException;
import main.java.model.join.PizzaJoin;
import main.java.model.join.TicketJoin;

import java.util.ArrayList;

public class TicketBusiness {
    private ITicketDataAccess ticketDataAccess;
    public TicketBusiness() {
        this.ticketDataAccess = new TicketDataAccess();
    }

    public TicketJoin getTicket(Integer id) throws DataAccessException {
        TicketJoin ticket = ticketDataAccess.getTicket(id);
        ticket.setPrice();
        return ticket;
    }

    public ArrayList<PizzaJoin> getPizzaFromBD(Integer id) throws DataAccessException{
        return ticketDataAccess.getPizzaFromBD(id);
    }
}
