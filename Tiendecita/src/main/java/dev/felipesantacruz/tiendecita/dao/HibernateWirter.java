package dev.felipesantacruz.tiendecita.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Represta un ejecutor de operaciones de escritura en una base de datos.
 * Esta operaciones se llevan a cabo bajo el amparo de la implementaci�n de 
 * <code>JPA</code> <code>Hibernate</code> 
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
public abstract class HibernateWirter
{

	private SessionFactory sessionFactory;
	
	/**
	 * Crea un ejecutor de operaciones que hace uso de la f�brica de sesiones de <code>Hibernate</code>
	 * <code>sessionFactory</code>
	 * @param sessionFactory
	 */
	protected HibernateWirter(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Devuelve la f�brica de sesiones que se est� utiliznado para crear las sesiones dentro
	 * de las cuales se ejecutan las operaciones de persistencia con el m�todo {@link #execute(WriteOperation).}
	 * @return una f�brica de sesiones de tipo {@link SessionFactory}
	 */
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	/**
	 * Ejecuta la operaci�n de escritura en repositorio de datos repersentada por <code>operation</code>
	 * @param operation operaci�n ejecutable de escritura en un respositorio de datos, que puede ser ejecutada
	 * a partir de un objetod e tipo {@link Session}
	 */
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
