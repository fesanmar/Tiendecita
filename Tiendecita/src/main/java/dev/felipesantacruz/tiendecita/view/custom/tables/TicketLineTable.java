package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;

/**
 * Tabla que contendrá los datos del modelo {@link TicketLine}. Es una subclase
 * de la clase abstracta {@link RefillableJTableTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0 
 */
public class TicketLineTable extends RefillableJTableTemplate<TicketLine>
{
	private static final long serialVersionUID = 1L;

	@Override
	protected NotEditableTableModelTemplate<TicketLine> createTableModel()
	{
		return new TicketLinesTableModel();
	}

	@Override
	protected TableModel createTableModel(Iterator<TicketLine> iterator)
	{
		return new TicketLinesTableModel(iterator);
	}

	@Override
	protected void setWidthForColumnNumber(int columnIndex, TableColumn column)
	{
		if (columnIndex == 0)
			removeColumn(column);
	}

}
