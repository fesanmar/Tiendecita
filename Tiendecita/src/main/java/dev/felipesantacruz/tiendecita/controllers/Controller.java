package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

public interface Controller<T>
{
	public void setActiveItem(T i);
	
	public T getActiveItem();
	
	public Iterator<T> fetchAll();
	
	public void insertActiveItem();
}
