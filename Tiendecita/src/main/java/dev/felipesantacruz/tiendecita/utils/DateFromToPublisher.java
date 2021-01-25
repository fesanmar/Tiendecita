package dev.felipesantacruz.tiendecita.utils;

import java.sql.Timestamp;

@FunctionalInterface
public interface DateFromToPublisher
{
	void update(Timestamp from, Timestamp to);
}
