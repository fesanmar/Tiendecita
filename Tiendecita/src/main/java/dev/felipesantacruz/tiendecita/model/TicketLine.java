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
 * Representa una línea dentro de un ticket de venta de <b>Tiendecita</b>.
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
	 * Crea una línea de ticket de venta sin ningún contenido
	 */
	public TicketLine()
	{ 
		// Constructor vacío requerido por Hibernate
	}

	/**
	 * Devuelve el ticket al que pertenece esta línea.
	 * @return el ticket que contiene a esta línea
	 */
	public Ticket getTicket()
	{
		return ticket;
	}

	/**
	 * Establece el ticket que albergará a esta línea
	 * @param ticket el ticket de venta que contendrá esta línea
	 */
	public void setTicket(Ticket ticket)
	{
		this.ticket = ticket;
	}

	/**
	 * Devuelve el artículo que integra esta línea del ticket de venta.
	 * @return el artíuclo de venta de esta línea.
	 */
	public Article getArticle()
	{
		return article;
	}

	/**
	 * Establece el artículo que integra esta línea del ticket de venta.
	 * @param article el artíuclo de venta de esta línea.
	 */
	public void setArticle(Article article)
	{
		this.article = article;
		priceInTicket = article.getPrice();
	}

	/**
	 * Devuelve el precio aplicado al artíuclo de esta línea.
	 * @return el precio que se aplica al artículo que integra esta línea del ticket de venta.
	 */
	public BigDecimal getPriceInTicket()
	{
		return priceInTicket;
	}

	/**
	 * Establece el precio que se aplicará al artículo en esta línea de ticket.
	 * @param priceInTicket el precio que se aplicará a la presente línea
	 */
	public void setPriceInTicket(BigDecimal priceInTicket)
	{
		this.priceInTicket = priceInTicket;
	}

	/**
	 * Devuelve la cantidad de artículos que se venden en esta línea de ticket.
	 * El artículo se establece mediante el método {@link #setArticle(Article)}
	 * @return la cantidad de unidades del artículo que se vende en esta línea de ticket
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * Establece el número de unidades que se venderá en esta línea de ticket.
	 * El artículo se establece mediante el método {@link #setArticle(Article)}
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Devuelve el <code>id</code> de la línea del ticket de venta.
	 * @return el identificador único, o <code>id</code> de esta línea de ticket
	 */
	public int getId()
	{
		return id;
	}
	
	
}
