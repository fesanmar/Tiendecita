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

	public TicketLine() { }

	public Ticket getTicket()
	{
		return ticket;
	}

	public void setTicket(Ticket ticket)
	{
		this.ticket = ticket;
	}

	public Article getArticle()
	{
		return article;
	}

	public void setArticle(Article article)
	{
		this.article = article;
		priceInTicket = article.getPrice();
	}

	public BigDecimal getPriceInTicket()
	{
		return priceInTicket;
	}

	public void setPriceInTicket(BigDecimal priceInTicket)
	{
		this.priceInTicket = priceInTicket;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getId()
	{
		return id;
	}
	
	
}
