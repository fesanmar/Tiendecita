package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;

/**
 * <code>TableModel</code> para gestionar los datos del modelo {@link Ticket} en unión con un
 * <code>JTable</code>. Es una subclase de 
 * {@link NotEditableTableModelTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0
 *
 */
public class TicketTableModel extends NotEditableTableModelTemplate<Ticket>
{
	private static final long serialVersionUID = 1L;
	private static String[] colNames = { "Fecha y hora", "importe" };

	/**
	 * Crea un <code>TableModel</code> para gestionar los datos el modelo {@link Ticket}.
	 * Será usado para rellenar las filas de una <code>JTable</code>.
	 */
	public TicketTableModel()
	{
		super(colNames);
	}
	
	/**
	 * Crea un <code>TableModel</code> para gestionar los datos el modelo {@link Ticket} y lo rellena
	 * con los datos encapsulados dentro de <code>articles</code>
	 * Será usado para rellenar las filas de una <code>JTable</code>.
	 * @param tickets un <code>Iterator</code> con los datos que poblarán las filas de este
	 * <code>TableModel</code>
	 * @see Iterator
	 */
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
