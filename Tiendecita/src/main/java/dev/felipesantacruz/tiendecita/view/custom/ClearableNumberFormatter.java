package dev.felipesantacruz.tiendecita.view.custom;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

/**
 * Formateador numérico con las siguientes propiedades:
 * <ul>
 * <li>Utiliza {@link BigDecimal}</li>
 * <li>Puede ser sobreescrito</li>
 * <li>Puede tomar valores de <code>BigDecimal.ZERO</code> hasta <code>99000.00</code></li>
 * <li>No permite caractérez inválidos</li>
 * <li>Si queda sólo un dígito, al borrarlo, se establece un <code>0</code> como valor</li>
 * </ul>
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class ClearableNumberFormatter extends NumberFormatter
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea un formateador numérico de tipo <code>BigDecimal</code>, cuyos números
	 * pueden ser sobreescritos y eliminados (todos)
	 * @see ClearableNumberFormatter 
	 */
	public ClearableNumberFormatter()
	{
		super(NumberFormat.getCurrencyInstance());
		setOverwriteMode(true);
		setValueClass(BigDecimal.class);
		setMinimum(BigDecimal.ZERO);
		setMaximum(BigDecimal.valueOf(99000.00));
		setAllowsInvalid(false);
		setCommitsOnValidEdit(true);
	}

	@Override
	public Object stringToValue(String text) throws ParseException
	{
		if (text.isEmpty())
			return BigDecimal.ZERO;
		return super.stringToValue(text);
	}
}
