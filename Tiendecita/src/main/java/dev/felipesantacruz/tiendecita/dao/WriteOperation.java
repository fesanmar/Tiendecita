package dev.felipesantacruz.tiendecita.dao;

import org.hibernate.Session;

@FunctionalInterface interface WriteOperation
{
	void executeWith(Session session);
}