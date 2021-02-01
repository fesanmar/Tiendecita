package dev.felipesantacruz.tiendecita.view.custom;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

/**
 * Campo de texto que hace uso de una máscara de entrada con el formato de fecha
 * <code>dd/mm/yyyy</code>.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class DateFormattedTextField extends JFormattedTextField
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea un campo de texto de fecha.
	 * @param columns el número de columnas que tendrá el campo de texto
	 * @throws ParseException si la máscara de entrada de fecha es incorrecta
	 */
	public DateFormattedTextField(int columns) throws ParseException
	{
		super(new DateMaskFormatter());
		setColumns(columns);
	}
}
