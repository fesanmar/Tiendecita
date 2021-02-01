package dev.felipesantacruz.tiendecita.view.custom;

import javax.swing.JFormattedTextField;

/**
 * Campo de texto sólo numérico, que no puede tomar valores menores de cero y
 * sobreescribible. Utiliza {@link ClearableNumberFormatter} como formateador.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class NumberTextField extends JFormattedTextField
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crar un campo de texto sólo número y sobreescribible.
	 */
	public NumberTextField()
	{
		super(new ClearableNumberFormatter());
	}
}
