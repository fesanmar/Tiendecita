package dev.felipesantacruz.tiendecita.view.custom;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

public class ClearableNumberFormatter extends NumberFormatter
{
	private static final long serialVersionUID = 1L;
	
	public ClearableNumberFormatter(NumberFormat format)
	{
		super(format);
	}
	
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
