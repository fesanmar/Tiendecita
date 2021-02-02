package dev.felipesantacruz.tiendecita.view.innerpanels;

/**
 * Observador de los eventos de aceptación de un cuadro de diálogo.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
@FunctionalInterface
public interface DialogAcceptedObserver
{
	/**
	 * Actualiza el estado del observador una vez producido el evento 
	 * de aceptación del cuadro de diálogo sujeto.
	 */
	void update();
}
