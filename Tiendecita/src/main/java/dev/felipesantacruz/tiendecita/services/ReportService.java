package dev.felipesantacruz.tiendecita.services;

/**
 * Representa un generador de informes.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
@FunctionalInterface
public interface ReportService
{
	/**
	 * Crea el informe
	 */
	void createReport();
}
