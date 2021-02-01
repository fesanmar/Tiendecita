package dev.felipesantacruz.tiendecita.view.custom;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

/**
 * M�scara de entrada para fechas, con el formato <code>dd/mm/yyyy</code>
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class DateMaskFormatter extends MaskFormatter
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crear una m�scara de entrada de fechas con el formato <code>dd/mm/yyyy</code>
	 * @throws ParseException si ocurre una error inesperado durante el an�lisis del patr�n
	 * <code>dd/mm/yyyy</code>
	 */
	public DateMaskFormatter() throws ParseException
	{
		super("##/##/####");
		setPlaceholderCharacter('_');
	}

}
