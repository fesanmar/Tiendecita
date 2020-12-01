package dev.felipesantacruz.tiendecita.view.innerpanels;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

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
import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NumberTextField;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;
import dev.felipesantacruz.tiendecita.view.custom.SearchTableForm;
import dev.felipesantacruz.tiendecita.view.custom.tables.TicketLineTable;
import dev.felipesantacruz.tiendecita.view.custom.tables.TicketTable;

public class TicketsPanel extends SearchTableForm<Ticket> implements DialogAcceptedObserver
{

	private static final long serialVersionUID = 1L;
	private RefillableJTableTemplate<Ticket> tableTickets;
	private RefillableJTableTemplate<TicketLine> tableLines;

	private JLabel lblDate;
	private JLabel lblLneas;
	private JLabel lblTotal;

	private JFormattedTextField tfTotal;
	private JTextField tfSearchDate;
	private JTextField tfDate;

	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnSave;
	private JButton btnNew;
	
	private TicketController ticketsController;
	private ArticleController articleController;
	
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
		lblDate = new JLabel("Fecha");
		lblDate.setBounds(254, 15, 46, 14);
		add(lblDate);

		lblLneas = new JLabel("L\u00EDneas");
		lblLneas.setBounds(254, 49, 46, 14);
		add(lblLneas);

		lblTotal = new JLabel("Total");
		lblTotal.setBounds(254, 243, 46, 14);
		add(lblTotal);
	}

	private void setUpTextFields()
	{
		tfSearchDate = new JFormattedTextField();
		tfSearchDate.setBounds(10, 12, 133, 20);
		add(tfSearchDate);

		tfDate = new JTextField();
		tfDate.setBounds(299, 12, 265, 20);
		tfDate.setEditable(false);
		add(tfDate);

		tfTotal = new NumberTextField();
		tfTotal.setEditable(false);
		tfTotal.setBounds(299, 240, 265, 20);
		add(tfTotal);
		tfTotal.setColumns(10);
	}

	private void setUpButtons()
	{
		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(164, 11, 65, 23);
		add(btnSearch);

		btnAdd = new JButton("+");
		btnAdd.setBounds(517, 49, 47, 83);
		add(btnAdd);

		btnRemove = new JButton("-");
		btnRemove.setBounds(517, 134, 47, 83);
		add(btnRemove);

		btnSave = new JButton("Guardar nuevo ticket");
		btnSave.setBounds(299, 271, 133, 23);
		add(btnSave);

		btnNew = new JButton("Nuevo");
		btnNew.setBounds(442, 271, 122, 23);
		add(btnNew);
	}

	private void setUpTables()
	{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 219, 251);
		add(scrollPane);

		tableTickets = new TicketTable();
		scrollPane.setViewportView(tableTickets);
		tableTickets.refill(ticketsController.fetchAll());

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(299, 49, 219, 168);
		add(scrollPane_1);

		tableLines = new TicketLineTable();
		scrollPane_1.setViewportView(tableLines);
	}
	
	private void setUpListeners()
	{
		ListSelectionModel selectionModel = tableTickets.getSelectionModel();
		selectionModel.addListSelectionListener(getTableSelectionListener());
		btnAdd.addActionListener(e -> openAddNewLineDialog());
		btnNew.addActionListener(e -> setFormInInsertMode());
		btnSave.addActionListener(e -> createNewTicket());
		btnRemove.addActionListener(e -> removeTicketLine());
		
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
		tableTickets.getSelectionModel().removeListSelectionListener(getTableSelectionListener());
		tableTickets.refill(ticketsController.fetchAll());
		tableTickets.getSelectionModel().addListSelectionListener(getTableSelectionListener());
		setFormInReadMode();
	}
	
	private void removeTicketLine()
	{
		Ticket ticket = ticketsController.getActiveItem();
		ticket.removeLine((TicketLine) tableLines.getModel().getValueAt(tableLines.getSelectedRow(), 0));
		update();
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
