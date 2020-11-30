package dev.felipesantacruz.tiendecita.dao;

import org.hibernate.Session;

@FunctionalInterface interface CRUDOperation
{
	void executeWith(Session session);
}