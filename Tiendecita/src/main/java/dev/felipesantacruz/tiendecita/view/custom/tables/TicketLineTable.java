package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;

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
		// TODO Auto-generated method stub

	}

}
