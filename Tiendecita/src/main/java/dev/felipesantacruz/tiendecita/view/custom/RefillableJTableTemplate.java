package dev.felipesantacruz.tiendecita.view.custom;

import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public abstract class RefillableJTableTemplate<T> extends JTable
{
	private static final long serialVersionUID = 1L;

	public RefillableJTableTemplate()
	{
		TableModel tm = createTableModel();
		setModel(tm);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	protected abstract NotEditableTableModelTemplate<T> createTableModel();


	public void refillAndSelect(Iterator<T> it, T item)
	{
		refill(it);
		selectRowThatContains(item);
	}
	
	public void refill(Iterator<T> iterator)
	{
		setModel(createTableModel(iterator));
	}

	private void selectRowThatContains(T item)
	{
		int indexToSelect = getIndexFor(item);
		setRowSelectionInterval(indexToSelect, indexToSelect);
	}

	private int getIndexFor(T item)
	{
		NotEditableTableModelTemplate<?> tm = (NotEditableTableModelTemplate<?>) getModel();
		return tm.getIndexOf(item);
	}

	protected abstract TableModel createTableModel(Iterator<T> iterator);

	@Override
	public void setModel(TableModel dataModel)
	{
		super.setModel(dataModel);
		resizeColumnsWidth();
	}

	private void resizeColumnsWidth()
	{
		for (int i = 0; i < getColumnCount(); i++)
		{
			TableColumn column = getColumnModel().getColumn(i);
			setWidthForColumnNumber(i, column);
		}
	}

	protected abstract void setWidthForColumnNumber(int columnIndex, TableColumn column);

}
