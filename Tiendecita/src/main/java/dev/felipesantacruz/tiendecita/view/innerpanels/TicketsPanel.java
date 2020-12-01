package dev.felipesantacruz.tiendecita.view.innerpanels;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dev.felipesantacruz.tiendecita.controllers.Controller;
import dev.felipesantacruz.tiendecita.controllers.TicketController;
import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.custom.NumberTextField;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;
import dev.felipesantacruz.tiendecita.view.custom.SearchTableForm;
import dev.felipesantacruz.tiendecita.view.custom.tables.TicketLineTable;
import dev.felipesantacruz.tiendecita.view.custom.tables.TicketTable;

public class TicketsPanel extends SearchTableForm<Ticket>
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
	private JButton btnLimpiarFormulario;
	
	private TicketController controller;
	
	public TicketsPanel(TicketController controller)
	{
		this.controller = controller;
		setLayout(null);
		setUpComponents();
		setUpListeners();
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

		btnLimpiarFormulario = new JButton("Limpiar formulario");
		btnLimpiarFormulario.setBounds(442, 271, 122, 23);
		add(btnLimpiarFormulario);
	}

	private void setUpTables()
	{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 219, 251);
		add(scrollPane);

		tableTickets = new TicketTable();
		scrollPane.setViewportView(tableTickets);
		tableTickets.refill(controller.fetchAll());

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
	}
	
	@Override
	protected void fillForm()
	{
		Ticket ticket = controller.getActiveItem();
		tfDate.setText(ticket.toString());
		tfTotal.setValue(ticket.getAmount());
		tableLines.refill(ticket.getTicketLines().iterator());
	}

	@Override
	protected JTable getSelectableTable()
	{
		return tableTickets;
	}

	@Override
	protected Controller<Ticket> getController()
	{
		return controller;
	}

}
