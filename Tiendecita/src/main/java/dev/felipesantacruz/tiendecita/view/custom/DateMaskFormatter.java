package dev.felipesantacruz.tiendecita.view.custom;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class DateMaskFormatter extends MaskFormatter
{
	private static final long serialVersionUID = 1L;
	
	public DateMaskFormatter() throws ParseException
	{
		super("##/##/####");
		setPlaceholderCharacter('_');
	}

}
