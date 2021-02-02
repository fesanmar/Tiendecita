package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.math.BigDecimal;
import java.util.Iterator;

import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;

/**
 * <TableModel> para gestionar los datos del modelo {@link TicketLine} en unión con un
 * <code>JTable</code>. Es una subclase de 
 * {@link NotEditableTableModelTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0
 *
 */
public class TicketLinesTableModel extends NotEditableTableModelTemplate<TicketLine>
{
	private static final long serialVersionUID = 1L;
	private static String[] colNames = { "Invisible", "Art.", "Cantidad", "Precio", "Total" };
	

	/**
	 * Crea un <TableModel> para gestionar los datos el modelo {@link TicketLine}.
	 * Será usado para rellenar las filas de una <code>JTable</code>.
	 */
	public TicketLinesTableModel()
	{
		super(colNames);
	}
	
	/**
	 * Crea un <TableModel> para gestionar los datos el modelo {@link TicketLine} y lo rellena
	 * con los datos encapsulados dentro de <code>iterator</code>
	 * Será usado para rellenar las filas de una <code>JTable</code>.
	 * @param iterator un <code>Iterator</code> con los datos que poblarán las filas de este
	 * <code>TableModel</code>
	 * @see Iterator
	 */
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
