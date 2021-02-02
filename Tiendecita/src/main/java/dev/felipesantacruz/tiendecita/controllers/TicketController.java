package dev.felipesantacruz.tiendecita.controllers;

import java.time.LocalDate;
import java.util.Iterator;

import dev.felipesantacruz.tiendecita.dao.TicketDAO;
import dev.felipesantacruz.tiendecita.model.Ticket;

/**
 * Implementación de la interfaz {@link Controller} para el model de datos {@link Ticket}
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
public class TicketController implements Controller<Ticket>
{
	private TicketDAO dao;
	private Ticket activeTicket;
	
	
	/**
	 * Crea el controlador que administra objetos de tipo {@link Ticket}. Utiliza para ello
	 * un objeto del tipo {@link TicketDAO}, en el que se definen las operaciones de recuperación
	 * y persistencia a realizar con el modelo de datos <code>Ticket</code>.
	 * @param dao componente en el se define cómo se relizarán las operaciones de recuperación
	 * y persistencia para los objetos de la clase <code>Ticket</code> gestionados por este
	 * controlador.
	 */
	public TicketController(TicketDAO dao)
	{
		this.dao = dao;
	}


	@Override
	public Iterator<Ticket> fetchAll()
	{
		return dao.findAll().iterator();
	}
	
	public Iterator<Ticket> fetchByDate(LocalDate d)
	{
		return dao.findByDate(d).iterator();
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
