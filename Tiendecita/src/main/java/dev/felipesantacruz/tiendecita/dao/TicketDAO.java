package dev.felipesantacruz.tiendecita.dao;

import java.util.Collection;

import dev.felipesantacruz.tiendecita.model.Ticket;

public interface TicketDAO
{
	Collection<Ticket> findAll();
	
	void insert(Ticket ticket);
}