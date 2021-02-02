package dev.felipesantacruz.tiendecita.view.custom;

import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * Subclase de <code>JTable</code> que facilita el rellenado de la tabla. Ofrece
 * métodos como {@link #refill(Iterator)} o {@link #refillAndSelect(Iterator, Object)}.
 * Esta tabla trabaja en conjunto con <code>TableModel</code> {@link NotEditableTableModelTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * @param <T> el modelo de datos de los elementos contenidos por la tabla
 */
public abstract class RefillableJTableTemplate<T> extends JTable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una tabla rellenable, con el modo de selección {@link ListSelectionModel#SINGLE_SELECTION}.
	 */
	protected RefillableJTableTemplate()
	{
		TableModel tm = createTableModel();
		setModel(tm);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * Crea un <code>TableModel</code> de tipo {@link NotEditableTableModelTemplate}
	 * y lo devuelve. Será utilizado por esta tabla para gestionar los datos.
	 * @return una <code>TableModel</code> que herede de {@link NotEditableTableModelTemplate}
	 */
	protected abstract NotEditableTableModelTemplate<T> createTableModel();


	/**
	 * Rellena la tabla con los datos proporcionados en <code>ite</code> y selecciona
	 * <code>item</code>. Si no se encuentra este elemento dentro del <code>TableModel</code>,
	 * se realizará la selección por defecto.
	 * @param it el <code>Iterator</code> que contendrá los datos con los que rellenar la tabla,
	 * de forma que cada elemento dentro del iterador, se mapeará a las columnas de una fila. Esto
	 * se realiza a través del método {@link NotEditableTableModelTemplate#fillNewRow(Object[], Object)}
	 * @param item el elemento que quedará seleccionado una vez la tabla haya sido rellenada. Si no existe,
	 * la tabla seguirá su comportamiento por defecto
	 */
	public void refillAndSelect(Iterator<T> it, T item)
	{
		refill(it);
		selectRowThatContains(item);
	}
	
	/**
	 * Rellena la tabla con los datos proporcionados en <code>ite</code>.
	 * @param iterator el <code>Iterator</code> que contendrá los datos con los que rellenar la tabla,
	 * de forma que cada elemento dentro del iterador, se mapeará a las columnas de una fila. Esto
	 * se realiza a través del método {@link NotEditableTableModelTemplate#fillNewRow(Object[], Object)}
	 */
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

	/**
	 * Crea un <code>TableModel</code> de tipo {@link NotEditableTableModelTemplate}
	 * lo rellena con los datos contenidos en <code>iterator</code> y lo devuelve. 
	 * Será utilizado por esta tabla para gestionar los datos.
	 * @param iterator el <code>Iterator</code> que contendrá los datos con los que rellenar la tabla,
	 * de forma que cada elemento dentro del iterador, se mapeará a las columnas de una fila. Esto
	 * se realiza a través del método {@link NotEditableTableModelTemplate#fillNewRow(Object[], Object)}
	 * @return una <code>TableModel</code> que herede de {@link NotEditableTableModelTemplate}
	 */
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

	/**
	 * Establece la anchura relativa de la columna situada en la posición 
	 * <code>columnIndex</code>.
	 * @param columnIndex la posición, dentro de la tabla, de la columna cuya anchura se establecerá
	 * @param column la representación de la columna cuya anchura se establecerá.
	 * @see TableColumn
	 */
	protected abstract void setWidthForColumnNumber(int columnIndex, TableColumn column);

}
