package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.dao.TicketDAO;
import dev.felipesantacruz.tiendecita.model.Ticket;

public class TicketController
{
	private TicketDAO dao;
	
	
	public TicketController(TicketDAO dao)
	{
		this.dao = dao;
	}


	public Iterator<Ticket> fetchAll()
	{
		return dao.findAll().iterator();
	}
}
