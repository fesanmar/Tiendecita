package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

/**
 * Representa el controlador de un modelo de datos.
 * Permite realizar operaciones como obtener todos los items del modelo,
 * fijar un �tem como el �tem activo, persistirlo y recuperarlo.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * @param <T> El tipo con el que va a operar el controlador, el que ser� usado 
 * como par�metro y como tipo de retorno en los m�todos.
 */
public interface Controller<T>
{
	
	/**
	 * Fija el item <code>i</code> como el elemento activo de este controlador
	 * @param i el �tem que se quiere fijar como elemento activo
	 */
	public void setActiveItem(T i);
	
	/**
	 * Recupera el elemento activo de este controlador
	 * @return El elemnto fijado como activo en este controlador, es decir
	 * el que previamente se haya fijado con el m�todo {@link #setActiveItem(Object)}
	 */
	public T getActiveItem();
	
	/**
	 * Recupera todos los elementos disponibles en este controlador. Por ejemplo, 
	 * podria devolver todos los �tems guardados en una base de datos, en un fichero 
	 * o s�mplemente en una lista.
	 * @return un <code>Iterator</code> con los elementos del tipo {@link T} administrados
	 * por este controlador 
	 */
	public Iterator<T> fetchAll();
	
	/**
	 * Toma el elemento fijado como activo y lo guarda en la colecci�n administrada
	 * por este controlador. Esta colecci�n podr�a ser una base de datos, un fichero,
	 * una <code>List</code>... El elemento activo ser� el que se haya pasado como par�metro
	 * al m�todo {@link #setActiveItem(Object)}
	 */
	public void insertActiveItem();
}
