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
	 * Crear un ticket de venta sin m�s informaci�n que la fecha y hora, para la que se toma
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
	 * @return el identificador �nico, o <code>id</code> del ticket
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Establece la fecha y la hora a la que se emite el ticket de venta.
	 * @param dt la fecha y la hora que se establecer� como fecha de emisi�n del ticket.
	 */
	public void setDateTicket(LocalDateTime dt)
	{
		dateTicket = dt;
	}

	/**
	 * Devuelve el momento de emisi�n del ticket de venta.
	 * @return la fecha y la hora a la que se emiti� el ticket de venta.
	 */
	public LocalDateTime getDateTicket()
	{
		return dateTicket;
	}
	
	/**
	 * A�ade una nueva l�nea de venta al ticket.
	 * @param line l�nea que se a�adir� al ticket
	 */
	public void addLine(TicketLine line)
	{
		lines.add(line);
		line.setTicket(this);
	}
	
	/**
	 * Elimina una l�nea del ticket.
	 * @param line la l�nea que ser� eliminada del ticket de venta
	 */
	public void removeLine(TicketLine line)
	{
		lines.remove(line);
		line.setTicket(null);
	}
	
	/**
	 * Devuelve todas las l�neas que componen el ticket de venta.
	 * @return una colecci�n con todas las l�neas del ticket. Si el ticket no tiene
	 * ninguna l�nea, devolver� una colecci�n vac�a
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
