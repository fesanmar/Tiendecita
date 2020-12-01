package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;

public class TicketTableModel extends NotEditableTableModelTemplate<Ticket>
{
	private static final long serialVersionUID = 1L;
	private static String[] colNames = { "Fecha y hora", "importe" };

	public TicketTableModel()
	{
		super(colNames);
	}
	
	public TicketTableModel(Iterator<Ticket> tickets)
	{
		super(colNames, tickets);
	}

	@Override
	protected void fillNewRow(Object[] row, Ticket ticket)
	{
		row[0] = ticket;
		row[1] = ticket.getAmount();
	}

}
