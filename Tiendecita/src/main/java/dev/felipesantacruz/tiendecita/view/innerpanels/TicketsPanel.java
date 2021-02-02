package dev.felipesantacruz.tiendecita.view.innerpanels;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dev.felipesantacruz.tiendecita.controllers.ArticleController;
import dev.felipesantacruz.tiendecita.controllers.Controller;
import dev.felipesantacruz.tiendecita.controllers.TicketController;
import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NumberTextField;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;
import dev.felipesantacruz.tiendecita.view.custom.SearchTableForm;
import dev.felipesantacruz.tiendecita.view.custom.dialogs.TicketLineDialog;
import dev.felipesantacruz.tiendecita.view.custom.tables.TicketLineTable;
import dev.felipesantacruz.tiendecita.view.custom.tables.TicketTable;

/**
 * Formulario para gestionar el model de datos {@link Ticket}.
 * Este formulario, como subclase de {@link SearchTableForm}, contiene
 * una tabla.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class TicketsPanel extends SearchTableForm<Ticket> implements DialogAcceptedObserver
{

	private static final long serialVersionUID = 1L;
	private RefillableJTableTemplate<Ticket> tableTickets;
	private RefillableJTableTemplate<TicketLine> tableLines;

	private JFormattedTextField tfTotal;
	private JTextField tfSearchDate;
	private JTextField tfDate;

	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnSave;
	private JButton btnNew;
	
	private transient TicketController ticketsController;
	private transient ArticleController articleController;
	
	/**
	 * Crea un formulario para gestionar el elementos de tipo
	 * {@link Ticket} a partir de controlador del tipo {@link ArticleController} y de
	 * {@linkplain TicketController}.
	 * @param controller controlador que será utilizado para realizar las operaciones
	 * recuperación y persistencia de los elemenos gestionados en este formulario
	 * @param articleController controlador del modelo de datos {@link Article} que serán utilizado
	 * para recuperar todos los artículos disponibles cuando sean necesarios
	 */
	public TicketsPanel(TicketController controller, ArticleController articleController)
	{
		this.ticketsController = controller;
		this.articleController = articleController;
		setLayout(null);
		setUpComponents();
		setUpListeners();
		disableAll();
	}

	private void setUpComponents()
	{
		setUpLabels();
		setUpTextFields();
		setUpButtons();
		setUpTables();
	}

	private void setUpLabels()
	{
		JLabel lblDate = new JLabel("Fecha");
		lblDate.setBounds(264, 15, 46, 14);
		add(lblDate);

		JLabel lblLneas = new JLabel("L\u00EDneas");
		lblLneas.setBounds(264, 49, 46, 14);
		add(lblLneas);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(264, 243, 46, 14);
		add(lblTotal);
	}

	private void setUpTextFields()
	{
		tfSearchDate = new JFormattedTextField();
		tfSearchDate.setBounds(10, 12, 133, 20);
		tfSearchDate.setToolTipText("Introduce una fecha con el formato dd/mm/yyy");
		add(tfSearchDate);

		tfDate = new JTextField();
		tfDate.setBounds(309, 12, 265, 20);
		tfDate.setEditable(false);
		add(tfDate);

		tfTotal = new NumberTextField();
		tfTotal.setEditable(false);
		tfTotal.setBounds(309, 240, 265, 20);
		add(tfTotal);
		tfTotal.setColumns(10);
	}

	private void setUpButtons()
	{
		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(164, 11, 80, 23);
		add(btnSearch);

		btnAdd = new JButton("+");
		btnAdd.setBounds(527, 49, 47, 83);
		add(btnAdd);

		btnRemove = new JButton("-");
		btnRemove.setBounds(527, 134, 47, 83);
		add(btnRemove);

		btnSave = new JButton("Guardar");
		btnSave.setBounds(309, 271, 133, 23);
		add(btnSave);

		btnNew = new JButton("Nuevo");
		btnNew.setBounds(452, 271, 122, 23);
		add(btnNew);
	}

	private void setUpTables()
	{
		JScrollPane ticketTablescrollPane = new JScrollPane();
		ticketTablescrollPane.setBounds(10, 43, 234, 251);
		add(ticketTablescrollPane);

		tableTickets = new TicketTable();
		ticketTablescrollPane.setViewportView(tableTickets);
		tableTickets.refill(ticketsController.fetchAll());

		JScrollPane ticketLineTablescrollPane = new JScrollPane();
		ticketLineTablescrollPane.setBounds(309, 49, 219, 168);
		add(ticketLineTablescrollPane);

		tableLines = new TicketLineTable();
		ticketLineTablescrollPane.setViewportView(tableLines);
	}
	
	private void setUpListeners()
	{
		ListSelectionModel selectionModel = tableTickets.getSelectionModel();
		selectionModel.addListSelectionListener(getTableSelectionListener());
		btnAdd.addActionListener(e -> openAddNewLineDialog());
		btnNew.addActionListener(e -> setFormInInsertMode());
		btnSave.addActionListener(e -> createNewTicket());
		btnRemove.addActionListener(e -> removeTicketLineIfSelected());
		btnSearch.addActionListener(e -> searchByDate());
		
	}
	
	private void openAddNewLineDialog()
	{
		TicketLineDialog lineDialog = new TicketLineDialog(ticketsController, articleController.fetchAll());
		lineDialog.setDialogAcceptedObserver(this);
		lineDialog.setVisible(true);
	}
	
	private void setFormInInsertMode()
	{
		clearForm();
		enableAll();
	}
	
	private void createNewTicket()
	{
		Ticket ticket = ticketsController.getActiveItem();
		if (ticket.getTicketLines().isEmpty())
			showLinesEmptyError();
		else 
			insertNewTicket(ticket);				
	}

	private void insertNewTicket(Ticket ticket)
	{
		ticket.setDateTicket(LocalDateTime.now());
		ticket.setAmount((BigDecimal) tfTotal.getValue());
		ticketsController.insertActiveItem();
		showSuccessMessage();
		refillTicketsTable(ticketsController.fetchAll());
		setFormInReadMode();
	}

	private void refillTicketsTable(Iterator<Ticket> data)
	{
		tableTickets.getSelectionModel().removeListSelectionListener(getTableSelectionListener());
		tableTickets.refill(data);
		tableTickets.getSelectionModel().addListSelectionListener(getTableSelectionListener());
	}
	
	private void removeTicketLineIfSelected()
	{
		int selectedRow = tableLines.getSelectedRow();
		if (anyTicketLineRowIsSelected(selectedRow)) 
			removeTicketLine(selectedRow);			
	}

	private boolean anyTicketLineRowIsSelected(int selectedRow)
	{
		return selectedRow != -1;
	}
	
	private void removeTicketLine(int selectedRow)
	{
		Ticket ticket = ticketsController.getActiveItem();
		ticket.removeLine((TicketLine) tableLines.getModel().getValueAt(selectedRow, 0));
		update();
	}
	
	private void searchByDate()
	{
		String possibleDate = tfSearchDate.getText();
		if (possibleDate.trim().isEmpty())
			refillTicketsTable(ticketsController.fetchAll());
		else
		{
			refillTicketTableWithTicketsOf(possibleDate);
		}
	}

	private void refillTicketTableWithTicketsOf(String possibleDate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try
		{
			LocalDate date = LocalDate.parse(possibleDate, formatter);
			refillTicketsTable(ticketsController.fetchByDate(date));									
		} catch (DateTimeParseException dtpe)
		{
			showDateEnteredError();
		}
	}
	
	private void setFormInReadMode()
	{
		clearForm();
		disableAll();
	}
	
	@Override
	protected void fillForm()
	{
		Ticket ticket = ticketsController.getActiveItem();
		tfDate.setText(ticket.toString());
		tfTotal.setValue(ticket.getAmount());
		tableLines.refill(ticket.getTicketLines().iterator());
		disableAll();
	}

	@Override
	protected JTable getSelectableTable()
	{
		return tableTickets;
	}

	@Override
	protected Controller<Ticket> getController()
	{
		return ticketsController;
	}
	
	private void disableAll()
	{
		btnAdd.setEnabled(false);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
	}
	
	private void clearForm()
	{
		Ticket ticket = new Ticket();
		ticketsController.setActiveItem(ticket);
		tfDate.setText("");
		tfTotal.setValue(null);
		tableLines.refill(ticket.getTicketLines().iterator());
		tableTickets.clearSelection();
	}
	
	private void enableAll()
	{
		btnAdd.setEnabled(true);
		btnRemove.setEnabled(true);
		btnSave.setEnabled(true);
	}
	
	private void showSuccessMessage()
	{
		JOptionPane.showMessageDialog(getParent(), "El ticket ha sido guardado con éxito.");
	}

	private void showLinesEmptyError()
	{
		JOptionPane.showMessageDialog(getParent(), "Por favor, introduzca alguna línea en el ticket.",
				"Error en los datos", JOptionPane.ERROR_MESSAGE);
	}
	
	private void showDateEnteredError()
	{
		JOptionPane.showMessageDialog(getParent(), "Por favor, introduzca una fecha válida con el formato dd/mm/yyyy.",
				"Error en los datos", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void update()
	{
		Collection<TicketLine> ticketLines = ticketsController.getActiveItem().getTicketLines();
		BigDecimal totalAmount = ticketLines
				.stream()
				.map(ticketLine -> ticketLine.getPriceInTicket().multiply(BigDecimal.valueOf(ticketLine.getQuantity())))
				.reduce((total, next) -> total.add(next))
				.orElse(BigDecimal.ZERO);
		tfTotal.setValue(totalAmount);
		tableLines.refill(ticketLines.iterator());
	}

}
