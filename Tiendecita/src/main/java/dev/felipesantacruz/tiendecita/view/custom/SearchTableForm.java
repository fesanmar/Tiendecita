package dev.felipesantacruz.tiendecita.view.custom;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dev.felipesantacruz.tiendecita.controllers.Controller;

public abstract class SearchTableForm <T> extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ListSelectionListener tableSelectionListener = this::manageTableSelection;
	
	protected ListSelectionListener getTableSelectionListener()
	{
		return tableSelectionListener;
	}
	
	private void manageTableSelection(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
			selectNewItem();
	}

	protected void selectNewItem()
	{
		@SuppressWarnings("unchecked")
		T item = (T) getSelectableTable().getValueAt(getSelectableTable().getSelectedRow(), 0);
		if (hasChanged(item))
			setNewItem(item);
		fillForm();
	}

	protected abstract JTable getSelectableTable();

	protected boolean hasChanged(T item)
	{
		return getController().getActiveItem() != item;
	}
	
	protected abstract Controller<T> getController();

	protected void setNewItem(T item)
	{
		getController().setActiveItem(item);
	}

	protected abstract void fillForm();
}
