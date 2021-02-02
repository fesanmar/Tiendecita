package dev.felipesantacruz.tiendecita.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utilidades para trabajar con fechas
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
public class DateUtil
{
	private DateUtil() { }
	
	/**
	 * Parsea <code>stringDate</code> y devuelve un <code>LocalDate</code> si contiene una fecha 
	 * en alguno de los formatos siguientes:
	 * <ul>
	 * <li><code>d/M[M]/yyyy</code></li>
	 * <li><code>d/M[M]/yy</code></li>
	 * <li><code>d-M[M]-yyyy</code></li>
	 * <li><code>d-M[M]-yy</code></li>
	 * </ul>
	 * @param stringDate la cadena de texto que contiene la fecha que se quiere parsear
	 * @return la representación de la fecha <code>stringDate</code> como {@link LocalDate}
	 * @throws DateTimeParseException si <code>stringDate</code> contiene un formato no válido
	 */
	public static LocalDate toLocalDate(String stringDate) throws DateTimeParseException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[d/M[M]/yyyy][d/M[M]/yy][d-M[M]-yyyy][d-M[M]-yy]");
		return LocalDate.parse(stringDate, formatter);
	}
}
