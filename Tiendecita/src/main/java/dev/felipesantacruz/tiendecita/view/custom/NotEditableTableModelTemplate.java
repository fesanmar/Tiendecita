package dev.felipesantacruz.tiendecita.view.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class NotEditableTableModelTemplate<T> extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private final String[] colNames;
	private Object[][] data = {};
	
	public NotEditableTableModelTemplate(String[] colNames)
	{
		this.colNames = colNames;
		
	}
	
	public NotEditableTableModelTemplate(String[] colNames, Iterator<T> iterator)
	{
		this.colNames = colNames;
		data = formatTableDataFrom(iterator);
	}
	
	private Object[][] formatTableDataFrom(Iterator<T> iterator)
	{
		List<Object[]> dataList = Collections.synchronizedList(new ArrayList<Object[]>());
		synchronized (iterator)
		{
			while (iterator.hasNext())
				addNewRowToList(iterator.next(), dataList);			
		}
		return listToArray(dataList);
	}
	
	private void addNewRowToList(T item, List<Object[]> dataList)
	{
		Object[] row = new Object[colNames.length];
		fillNewRow(row, item);
		dataList.add(row);
	}
	
	protected abstract void fillNewRow(Object[] row, T item);
	
	private  Object[][] listToArray(List<Object[]> dataList)
	{
		Object[][] formatedTable = new Object[dataList.size()][colNames.length];
		return dataList.toArray(formatedTable);
	}
	
	protected String[] getColNames()
	{
		return colNames;
	}
	
	protected Object[][] getData()
	{
		return data;
	}
	
	@Override
	public String getColumnName(int column)
	{
		return colNames[column];
	}

	@Override
	public int getColumnCount()
	{
		return colNames.length;
	}

	@Override
	public int getRowCount()
	{
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}
	
	public int getIndexOf(Object element)
	{
		for (int row = 0; row < data.length; row++)
		{
			if (rowContainsElement(row, element))
				return row;
		}
		return -1;
	}

	private boolean rowContainsElement(int row, Object element)
	{
		return data[row][0].equals(element);
	}
}
