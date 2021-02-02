package dev.felipesantacruz.tiendecita.view.innerpanels;

/**
 * Observador de los eventos de aceptaci�n de un cuadro de di�logo.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
@FunctionalInterface
public interface DialogAcceptedObserver
{
	/**
	 * Actualiza el estado del observador una vez producido el evento 
	 * de aceptaci�n del cuadro de di�logo sujeto.
	 */
	void update();
}
