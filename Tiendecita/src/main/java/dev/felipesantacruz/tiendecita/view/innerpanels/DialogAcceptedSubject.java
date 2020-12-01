package dev.felipesantacruz.tiendecita.view.innerpanels;

public interface DialogAcceptedSubject
{
	void setDialogAcceptedObserver(DialogAcceptedObserver o);
	
	void notifyObserver();
}
