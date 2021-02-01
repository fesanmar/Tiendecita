package dev.felipesantacruz.tiendecita.exceptions;

/**
 * Representa una excepción producida durante la creación de un informe.
 * @author Felipe Santa-Cruz
 *
 */
public class ReportConstructionException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Crear una excepción de construcción de informe.
	 * @param arg el mensaje que será mostrado en el cuerpo de la excepción.
	 */
	public ReportConstructionException(String arg)
	{
		super(arg);
	}

}
