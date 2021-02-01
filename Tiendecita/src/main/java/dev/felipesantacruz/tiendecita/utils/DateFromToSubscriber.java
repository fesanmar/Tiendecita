package dev.felipesantacruz.tiendecita.utils;

import java.sql.Timestamp;

/**
 * Representa un suscriptor que quiere ser avisado cada vez se publica
 * un nuevo par de fechas: fecha desde, fecha hasta.
 * @author Felipe Santa-Cruz
 *
 */
@FunctionalInterface
public interface DateFromToSubscriber
{
	/**
	 * Recibe la fecha desde y hasta establecida.
	 * @param from la fecha y hora que se ha establecido como <i>desde</i>
	 * @param to la fecha y hora que se ha establecido como <i>hasta</i>
	 */
	void update(Timestamp from, Timestamp to);
}
