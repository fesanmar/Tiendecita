package dev.felipesantacruz.tiendecita.dao;

import org.hibernate.Session;

/**
 * Representa una operaci�n de escritura en una base de datos.
 * @author Felipe Santa-Cruz
 *
 */
@FunctionalInterface interface WriteOperation
{
	/**
	 * Ejecuta una operaci�n de persistencia en una base de datos, usando
	 * para ello el objetco <code>session</code> que recibe por par�metro.
	 * @param session objeto de hibernate que ser� usado para realizar la
	 * operaci�n de persistencia
	 */
	void executeWith(Session session);
}