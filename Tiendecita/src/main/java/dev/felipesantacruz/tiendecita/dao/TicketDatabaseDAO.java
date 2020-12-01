package dev.felipesantacruz.tiendecita.dao;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.felipesantacruz.tiendecita.model.Ticket;

public class TicketDatabaseDAO extends HibernateWirter implements TicketDAO
{
	
	public TicketDatabaseDAO(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}

	@Override
	public Collection<Ticket> findAll()
	{
		Session session = getSessionFactory().openSession();
		Collection<Ticket> tickets = session.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
		session.close();
		return tickets;
	}

	@Override
	public void insert(Ticket ticket)
	{
		execute(s -> s.save(ticket));
	}
	
	@Override
	public Collection<Ticket> findByDate(LocalDate date)
	{
		
		return findAll()
				.stream()
				.filter(ticket -> ticket.getDateTicket().getDayOfMonth() == date.getDayOfMonth())
				.filter(ticket -> ticket.getDateTicket().getMonthValue() == date.getMonthValue())
				.filter(ticket -> ticket.getDateTicket().getYear() == date.getYear())
				.collect(Collectors.toList());
	}

}
