package dev.felipesantacruz.tiendecita.view.innerpanels;

/**
 * Representa la monitorización del evento aceptar de un cuadro de diálogo.
 * La clase que lo implement debe decidir qué evento concreto y de qué componente
 * se vinculará con el evento de aceptación.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public interface DialogAcceptedSubject
{
	/**
	 * Establece el observador que será avisado cuando se produzca el evento
	 * de aceptación.
	 * @param o implementación de {@link DialogAcceptedObserver} que será avisado
	 * cuando se produzca el evento de aceptación del cuadro de diálogo
	 */
	void setDialogAcceptedObserver(DialogAcceptedObserver o);
	
	/**
	 * Notifica al observador del evento de aceptación del cuadro de diálogo
	 * de que se ha producido dicho evento. 
	 */
	void notifyObserver();
}
