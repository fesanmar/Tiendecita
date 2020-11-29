package dev.felipesantacruz.tiendecita.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TicketLine> lines = new ArrayList<>();

	public Ticket() 
	{
		this.dateTicket = LocalDateTime.now();
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public int getId()
	{
		return id;
	}
	
	public void setDateTicket(LocalDateTime dt)
	{
		dateTicket = dt;
	}

	public LocalDateTime getDateTicket()
	{
		return dateTicket;
	}
	
	public void addLine(TicketLine line)
	{
		lines.add(line);
		line.setTicket(this);
	}
	
	public void removeLine(TicketLine line)
	{
		lines.remove(line);
		line.setTicket(null);
	}
}
