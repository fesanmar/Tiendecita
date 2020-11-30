package dev.felipesantacruz.tiendecita.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.felipesantacruz.tiendecita.model.Ticket;

public class TicketDatabaseDAO implements TicketDAO
{
	private SessionFactory sessionFactory;
	public TicketDatabaseDAO(SessionFactory factory)
	{
		this.sessionFactory = factory;
	}
	@Override
	public Collection<Ticket> findAll()
	{
		Session session = sessionFactory.openSession();
		Collection<Ticket> tickets = session.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
		session.close();
		return tickets;
	}

	@Override
	public void insert(Ticket ticket)
	{
		// TODO Auto-generated method stub

	}

}
