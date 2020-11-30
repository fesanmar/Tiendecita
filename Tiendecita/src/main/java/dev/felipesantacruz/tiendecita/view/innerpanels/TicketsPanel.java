package dev.felipesantacruz.tiendecita.view.innerpanels;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dev.felipesantacruz.tiendecita.controllers.TicketController;
import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;
import dev.felipesantacruz.tiendecita.view.custom.TicketTable;

public class TicketsPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private RefillableJTableTemplate<Ticket> tableTickets;
	private JTable tableLines;

	private JLabel lblDate;
	private JLabel lblLneas;
	private JLabel lblTotal;

	private JTextField textField;
	private JFormattedTextField txtSearchDate;
	private JFormattedTextField tfDate;

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
		txtSearchDate = new JFormattedTextField();
		txtSearchDate.setBounds(10, 12, 133, 20);
		add(txtSearchDate);

		tfDate = new JFormattedTextField();
		tfDate.setBounds(299, 12, 265, 20);
		add(tfDate);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(299, 240, 265, 20);
		add(textField);
		textField.setColumns(10);
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

		tableLines = new JTable(new String[][] {}, new String[] { "Art.", "Cantidad", "Precio", "Total" });
		scrollPane_1.setViewportView(tableLines);
	}

}
