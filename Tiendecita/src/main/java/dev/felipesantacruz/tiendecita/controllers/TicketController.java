package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.dao.TicketDAO;
import dev.felipesantacruz.tiendecita.model.Ticket;

public class TicketController implements Controller<Ticket>
{
	private TicketDAO dao;
	private Ticket activeTicket;
	
	
	public TicketController(TicketDAO dao)
	{
		this.dao = dao;
	}


	@Override
	public Iterator<Ticket> fetchAll()
	{
		return dao.findAll().iterator();
	}


	@Override
	public void setActiveItem(Ticket t)
	{
		activeTicket = t;
	}


	@Override
	public Ticket getActiveItem()
	{
		return activeTicket;
	}


	@Override
	public void insertActiveItem()
	{
		dao.insert(activeTicket);
	}
}
