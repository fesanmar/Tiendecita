package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;

/**
 * Tabla que contendrá los datos del modelo {@link Ticket}. Es una subclase
 * de la clase abstracta {@link RefillableJTableTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0 
 */
public class TicketTable extends RefillableJTableTemplate<Ticket>
{
	private static final long serialVersionUID = 1L;

	@Override
	protected NotEditableTableModelTemplate<Ticket> createTableModel()
	{
		return new TicketTableModel();
	}

	@Override
	protected TableModel createTableModel(Iterator<Ticket> iterator)
	{
		return new TicketTableModel(iterator);
	}

	@Override
	protected void setWidthForColumnNumber(int columnIndex, TableColumn column) 
	{
		switch (columnIndex)
		{
		case 0:
			column.setPreferredWidth(90);
			break;
		case 1:
			column.setPreferredWidth(10);
			break;
		}
	}

}
