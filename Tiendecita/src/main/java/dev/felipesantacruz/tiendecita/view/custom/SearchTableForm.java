package dev.felipesantacruz.tiendecita.view.custom;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dev.felipesantacruz.tiendecita.controllers.Controller;

/**
 * Abstracción de un formulario que contiene una tabla en la que se hacen búsquedas y de la
 * que se extraen datos. Al ser una clase abstracta, debe ser extendida para ser usada.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * @param <T> el modelo de datos que se gestionará dentro del formulario.
 */
public abstract class SearchTableForm <T> extends JPanel
{
	private static final long serialVersionUID = 1L;
	private transient ListSelectionListener tableSelectionListener = this::manageTableSelection;
	
	/**
	 * Devuelve el objeto que se está utilizando para escuchar los cambios de la tabla
	 * incluida en este formulario.
	 * @return el objeto del tipo {@link ListSelectionListener} que se está utilizando para
	 * conocer cuándo un evento de selección en la tabla incluida en est formulario
	 */
	protected ListSelectionListener getTableSelectionListener()
	{
		return tableSelectionListener;
	}
	
	private void manageTableSelection(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			selectNewItem();
	}

	private void selectNewItem()
	{
		@SuppressWarnings("unchecked")
		T item = (T) getSelectableTable().getValueAt(getSelectableTable().getSelectedRow(), 0);
		if (hasChanged(item))
			setNewItem(item);
		fillForm();
	}

	/**
	 * Devuelve la tabla incluida en este formulario.
	 * @return la tabla, del tipo {@link JTable} que se está utilizando en este formulario.
	 */
	protected abstract JTable getSelectableTable();

	private boolean hasChanged(T item)
	{
		return getController().getActiveItem() != item;
	}
	
	/**
	 * Devuelve el controlador que está gestionando el modelo en este formulario.
	 * @return el {@link Controller}} que gestiona el modelo de datos de este formulario.
	 */
	protected abstract Controller<T> getController();

	private void setNewItem(T item)
	{
		getController().setActiveItem(item);
	}

	
	/**
	 * Rellena la parte no tabular del formulario. Este método será llamado
	 * cada vez que se produczca un evento de selección en la tabla.
	 */
	protected abstract void fillForm();
}
