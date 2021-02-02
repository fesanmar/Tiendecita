package dev.felipesantacruz.tiendecita.dao;

import java.time.LocalDate;
import java.util.Collection;

import dev.felipesantacruz.tiendecita.model.Ticket;

/**
 * Representa el conjunto de operaciones de consulta y persistencia 
 * que se pueden realizar sobre el modelo de datos {@link Ticket}
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
public interface TicketDAO
{
	/**
	 * Devuelve todos los elementos disponibles disponibles en el repositorio de datos 
	 * del tipo {@link Ticket}.
	 * @return un objeto de tipo <code>Collection</code> con todos los objetos
	 * del tipo <code>Ticket</code> disponible en el repositorio de datos. 
	 */
	Collection<Ticket> findAll();
	
	/**
	 * Inserta un nuevo ticket en el repositorio de datos.
	 * @param ticket el elemento que se quiere persistir
	 */
	void insert(Ticket ticket);

	/**
	 * Devuelve todos los tickets cuya fecha coincida con <code>date</code>
	 * @param date la fecha de la que se quieren obtener todos los tickets
	 * @return un objeto del tipo <code>Collection</code> con tods los tickets
	 * cuya fecha coincida con <code>date</code>
	 */
	Collection<Ticket> findByDate(LocalDate date);
}
