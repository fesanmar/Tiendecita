package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.math.BigDecimal;
import java.util.Iterator;

import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;

public class TicketLinesTableModel extends NotEditableTableModelTemplate<TicketLine>
{
	private static final long serialVersionUID = 1L;
	private static String[] colNames = { "Invisible", "Art.", "Cantidad", "Precio", "Total" };
	
	public TicketLinesTableModel()
	{
		super(colNames);
	}
	
	public TicketLinesTableModel(Iterator<TicketLine> iterator)
	{
		super(colNames, iterator);
	}
	
	@Override
	protected void fillNewRow(Object[] row, TicketLine ticketLine)
	{
		row[0] = ticketLine;
		row[1] = ticketLine.getArticle();
		row[2] = ticketLine.getQuantity();
		row[3] = ticketLine.getPriceInTicket();
		row[4] = ticketLine
				.getPriceInTicket()
				.multiply(BigDecimal.valueOf(ticketLine.getQuantity()));
	}

}
