package dev.felipesantacruz.tiendecita.exceptions;

/**
 * Representa una excepci�n producida durante la creaci�n de un informe.
 * @author Felipe Santa-Cruz
 *
 */
public class ReportConstructionException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Crear una excepci�n de construcci�n de informe.
	 * @param arg el mensaje que ser� mostrado en el cuerpo de la excepci�n.
	 */
	public ReportConstructionException(String arg)
	{
		super(arg);
	}

}
