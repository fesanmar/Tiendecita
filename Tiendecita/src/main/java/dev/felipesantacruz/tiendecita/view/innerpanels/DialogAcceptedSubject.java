package dev.felipesantacruz.tiendecita.view.innerpanels;

/**
 * Representa la monitorizaci�n del evento aceptar de un cuadro de di�logo.
 * La clase que lo implement debe decidir qu� evento concreto y de qu� componente
 * se vincular� con el evento de aceptaci�n.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public interface DialogAcceptedSubject
{
	/**
	 * Establece el observador que ser� avisado cuando se produzca el evento
	 * de aceptaci�n.
	 * @param o implementaci�n de {@link DialogAcceptedObserver} que ser� avisado
	 * cuando se produzca el evento de aceptaci�n del cuadro de di�logo
	 */
	void setDialogAcceptedObserver(DialogAcceptedObserver o);
	
	/**
	 * Notifica al observador del evento de aceptaci�n del cuadro de di�logo
	 * de que se ha producido dicho evento. 
	 */
	void notifyObserver();
}
