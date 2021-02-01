package dev.felipesantacruz.tiendecita.dao;

import org.hibernate.Session;

/**
 * Representa una operación de escritura en una base de datos.
 * @author Felipe Santa-Cruz
 *
 */
@FunctionalInterface interface WriteOperation
{
	/**
	 * Ejecuta una operación de persistencia en una base de datos, usando
	 * para ello el objetco <code>session</code> que recibe por parámetro.
	 * @param session objeto de hibernate que será usado para realizar la
	 * operación de persistencia
	 */
	void executeWith(Session session);
}