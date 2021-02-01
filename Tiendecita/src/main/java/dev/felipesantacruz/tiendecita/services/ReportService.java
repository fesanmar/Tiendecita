package dev.felipesantacruz.tiendecita.services;

/**
 * Representa un generador de informes.
 * @author Felipe Santa-Cruz
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
