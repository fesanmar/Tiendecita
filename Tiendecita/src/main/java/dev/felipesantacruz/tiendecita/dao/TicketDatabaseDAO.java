package dev.felipesantacruz.tiendecita.dao;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.felipesantacruz.tiendecita.model.Ticket;

/**
 * Representa el enlace con un repositorio de elementos de tipo {@link Ticket}, en el que se pueden realizar
 * diversas operaciones de persistencia y de lecutura.
 * @author Felipe Santa-Cruz
 *
 */
public class TicketDatabaseDAO extends HibernateWirter implements TicketDAO
{
	/**
	 * Crea un enalce con el repositorio de  datos de tipo {@link Ticket}, para realizar,
	 * a tavés de sus métodos, operaciones de persistencia y lectura.
	 * @param sessionFactory factoría de sesiones que se usará para crear las sesiones, a través
	 * de las que se realizarán las operaciones de persistencia y lectura con el reapositorio.
	 */
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
