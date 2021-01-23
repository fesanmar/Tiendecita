package dev.felipesantacruz.tiendecita.view.custom;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

public class DateFormattedTextField extends JFormattedTextField
{
	private static final long serialVersionUID = 1L;
	
	public DateFormattedTextField(int columns) throws ParseException
	{
		super(new DateMaskFormatter());
		setColumns(columns);
	}
}
