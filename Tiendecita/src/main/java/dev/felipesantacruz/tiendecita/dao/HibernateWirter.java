package dev.felipesantacruz.tiendecita.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class HibernateWirter
{

	private SessionFactory sessionFactory;
	
	public HibernateWirter(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	protected void execute(WriteOperation operation)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		operation.executeWith(session);
		session.flush();
		transaction.commit();
		session.close();
	}
}
