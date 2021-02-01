package dev.felipesantacruz.tiendecita.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Representa un ticket de venta de <b>Tiendecita</b>.
 * @author Felipe Santa-Cruz
 *
 */
@Entity
@Table(name = "ticket")
public class Ticket
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "date_ticket" )
	private LocalDateTime dateTicket;
	
	private BigDecimal amount;
	
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.EAGER)
	private List<TicketLine> lines = new ArrayList<>();

	/**
	 * Crear un ticket de venta sin más información que la fecha y hora, para la que se toma
	 * el momento actual. 
	 */
	public Ticket() 
	{
		this.dateTicket = LocalDateTime.now();
	}

	/**
	 * Devuelve al importe total de ticket.
	 * @return un <code>BigDecimal</code> que representa la suma de todas compras
	 * incluidas den el ticket
	 */
	public BigDecimal getAmount()
	{
		return amount;
	}

	/**
	 * Establece el importe total de ticket.
	 * @param amount el total de las compras incluidas en el ticket.
	 */
	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	/**
	 * Devuelve el <code>id</code> del ticket.
	 * @return el identificador único, o <code>id</code> del ticket
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Establece la fecha y la hora a la que se emite el ticket de venta.
	 * @param dt la fecha y la hora que se establecerá como fecha de emisión del ticket.
	 */
	public void setDateTicket(LocalDateTime dt)
	{
		dateTicket = dt;
	}

	/**
	 * Devuelve el momento de emisión del ticket de venta.
	 * @return la fecha y la hora a la que se emitió el ticket de venta.
	 */
	public LocalDateTime getDateTicket()
	{
		return dateTicket;
	}
	
	/**
	 * Añade una nueva línea de venta al ticket.
	 * @param line línea que se añadirá al ticket
	 */
	public void addLine(TicketLine line)
	{
		lines.add(line);
		line.setTicket(this);
	}
	
	/**
	 * Elimina una línea del ticket.
	 * @param line la línea que será eliminada del ticket de venta
	 */
	public void removeLine(TicketLine line)
	{
		lines.remove(line);
		line.setTicket(null);
	}
	
	/**
	 * Devuelve todas las líneas que componen el ticket de venta.
	 * @return una colección con todas las líneas del ticket. Si el ticket no tiene
	 * ninguna línea, devolverá una colección vacía
	 */
	public Collection<TicketLine> getTicketLines()
	{
		return lines;
	}
	
	@Override
	public String toString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return dateTicket.format(formatter).toString();
	}
}
