package dev.felipesantacruz.tiendecita.view.custom;

import javax.swing.JFormattedTextField;

public class NumberTextField extends JFormattedTextField
{
	private static final long serialVersionUID = 1L;
	
	public NumberTextField()
	{
		super(new ClearableNumberFormatter());
	}
}
