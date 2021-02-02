package dev.felipesantacruz.tiendecita.view.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Modelo para tablas con celdas no editables.
 * Esta clase facilita el uso de las tablas de <code>Swing</code>, siendo necesario
 * impolementar sólo el método {@link NotEditableTableModelTemplate#fillNewRow(Object[], Object)}.
 * Trabaja en conjunto con {@link RefillableJTableTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * @param <T> el modelo de datos que se quiere gestionar con este <code>TableModel</code>
 */
public abstract class NotEditableTableModelTemplate<T> extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private final String[] colNames;
	private Object[][] data = {};
	
	/**
	 * Crea un <code>TableModel</code> no editable con las cabeceras establecidas en
	 * <code>colNames</code>
	 * @param colNames las cabeceras que tendrá la tabla que use este componente como <code>TableModel</code>
	 */
	protected NotEditableTableModelTemplate(String[] colNames)
	{
		this.colNames = colNames;
		
	}
	
	/**
	 * Crea un <code>TableModel</code> no editable con las cabeceras establecidas en
	 * <code>colNames</code>, y la rellena con los datos contenidos en <code>iterator</code>
	 * @param colNames las cabeceras que tendrá la tabla que use este componente como <code>TableModel</code>
	 * @param iterator el iterador con los datos que se usarán para rellenar la tabla que use este 
	 * componente como <code>TableModel</code>
	 */
	protected NotEditableTableModelTemplate(String[] colNames, Iterator<T> iterator)
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
	
	/**
	 * Rellena <code>row</code> con los datos contenidos en <code>item</code>.
	 * Este cambio se hará palpable también en la tabla que use este componente como 
	 * <code>TableModel</code>. 
	 * @param row la lista que será rellenada con los datos de <code>item</code>
	 * @param item el contenedor con cuyos datos se poblará <code>row</code>
	 */
	protected abstract void fillNewRow(Object[] row, T item);
	
	private  Object[][] listToArray(List<Object[]> dataList)
	{
		Object[][] formatedTable = new Object[dataList.size()][colNames.length];
		return dataList.toArray(formatedTable);
	}
	
	/**
	 * Devuelve las cabeceras de este <code>TableModel</code>
	 * @return un <code>Array</code> con los nombres de las cabeceras de este <code>TableModel</code>
	 * y, por lo tanto, de la table que le esté dando uso 
	 */
	protected String[] getColNames()
	{
		return colNames;
	}
	
	/**
	 * Devuelve un <code>Array</code> bidimensional con los datos contenidos en este <code>TableModel</code>
	 * y, por lo tanto, de la table que le esté dando uso 
	 * @return <code>Array</code> bidimensional que contiene todos los tados del modelo de datos
	 */
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
	
	/**
	 * Devuelve el índice, es decir, el número de fila, que ocupa <code>element</code> dentro
	 * del <code>TableModel</code> y, por lo tanto, de la table que le esté dando uso 
	 * @param element el ítem del que se quiere conocer la ubicación
	 * @return el número de fila que ocupa <code>element</code> dentro del <code>TableModel</code>.
	 * Devolverá <code>-1</code> en el caso de que <code>element</code> no sea encontrado
	 */
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
