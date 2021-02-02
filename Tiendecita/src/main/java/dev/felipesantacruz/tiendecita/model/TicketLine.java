 package dev.felipesantacruz.tiendecita.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa una l�nea dentro de un ticket de venta de <b>Tiendecita</b>.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
@Entity
@Table(name = "ticketline")
public class TicketLine
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false, updatable = false)
	private Ticket ticket;
	
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false, updatable = true)
	private Article article;
	
	@Column(name = "ticket_price")
	private BigDecimal priceInTicket;
	
	private int quantity;

	/**
	 * Crea una l�nea de ticket de venta sin ning�n contenido
	 */
	public TicketLine()
	{ 
		// Constructor vac�o requerido por Hibernate
	}

	/**
	 * Devuelve el ticket al que pertenece esta l�nea.
	 * @return el ticket que contiene a esta l�nea
	 */
	public Ticket getTicket()
	{
		return ticket;
	}

	/**
	 * Establece el ticket que albergar� a esta l�nea
	 * @param ticket el ticket de venta que contendr� esta l�nea
	 */
	public void setTicket(Ticket ticket)
	{
		this.ticket = ticket;
	}

	/**
	 * Devuelve el art�culo que integra esta l�nea del ticket de venta.
	 * @return el art�uclo de venta de esta l�nea.
	 */
	public Article getArticle()
	{
		return article;
	}

	/**
	 * Establece el art�culo que integra esta l�nea del ticket de venta.
	 * @param article el art�uclo de venta de esta l�nea.
	 */
	public void setArticle(Article article)
	{
		this.article = article;
		priceInTicket = article.getPrice();
	}

	/**
	 * Devuelve el precio aplicado al art�uclo de esta l�nea.
	 * @return el precio que se aplica al art�culo que integra esta l�nea del ticket de venta.
	 */
	public BigDecimal getPriceInTicket()
	{
		return priceInTicket;
	}

	/**
	 * Establece el precio que se aplicar� al art�culo en esta l�nea de ticket.
	 * @param priceInTicket el precio que se aplicar� a la presente l�nea
	 */
	public void setPriceInTicket(BigDecimal priceInTicket)
	{
		this.priceInTicket = priceInTicket;
	}

	/**
	 * Devuelve la cantidad de art�culos que se venden en esta l�nea de ticket.
	 * El art�culo se establece mediante el m�todo {@link #setArticle(Article)}
	 * @return la cantidad de unidades del art�culo que se vende en esta l�nea de ticket
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Establece el n�mero de unidades que se vender� en esta l�nea de ticket.
	 * El art�culo se establece mediante el m�todo {@link #setArticle(Article)}
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Devuelve el <code>id</code> de la l�nea del ticket de venta.
	 * @return el identificador �nico, o <code>id</code> de esta l�nea de ticket
	 */
	public int getId()
	{
		return id;
	}
	
	
}
