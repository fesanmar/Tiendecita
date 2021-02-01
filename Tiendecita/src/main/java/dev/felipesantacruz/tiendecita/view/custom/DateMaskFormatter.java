package dev.felipesantacruz.tiendecita.view.custom;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

/**
 * Máscara de entrada para fechas, con el formato <code>dd/mm/yyyy</code>
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class DateMaskFormatter extends MaskFormatter
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crear una máscara de entrada de fechas con el formato <code>dd/mm/yyyy</code>
	 * @throws ParseException si ocurre una error inesperado durante el análisis del patrón
	 * <code>dd/mm/yyyy</code>
	 */
	public DateMaskFormatter() throws ParseException
	{
		super("##/##/####");
		setPlaceholderCharacter('_');
	}

}
