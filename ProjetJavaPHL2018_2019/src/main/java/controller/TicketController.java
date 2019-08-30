package main.java.controller;

import main.java.business.TicketBusiness;
import main.java.exception.DataAccessException;
import main.java.model.join.PizzaJoin;
import main.java.model.join.TicketJoin;

import java.util.ArrayList;

public class TicketController {
    private TicketBusiness ticketBusiness;

    public TicketController() {
        this.ticketBusiness = new TicketBusiness();
    }


    public TicketJoin getTicket(Integer id) throws DataAccessException {
        return ticketBusiness.getTicket(id);
    }

    public ArrayList<PizzaJoin> getPizzaFromBD(Integer id) throws DataAccessException{
        return ticketBusiness.getPizzaFromBD(id);
    }
}
