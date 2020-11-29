package dev.felipesantacruz.tiendecita.view.innerpanels;

import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TicketsPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	private JTable tableTickets;
	private JTable tableLines;
	private JTextField textField;

	public TicketsPanel()
	{
		setLayout(null);

		JFormattedTextField txtSearchDate = new JFormattedTextField();
		txtSearchDate.setBounds(10, 12, 133, 20);
		add(txtSearchDate);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(164, 11, 65, 23);
		add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 219, 251);
		add(scrollPane);

		tableTickets = new JTable(new String[][] {}, new String[] { "Fecha y hora", "importe" });
		scrollPane.setViewportView(tableTickets);
		
		JLabel lblDate = new JLabel("Fecha");
		lblDate.setBounds(254, 15, 46, 14);
		add(lblDate);
		
		JFormattedTextField tfDate = new JFormattedTextField();
		tfDate.setBounds(299, 12, 265, 20);
		add(tfDate);
		
		JLabel lblLneas = new JLabel("L\u00EDneas");
		lblLneas.setBounds(254, 49, 46, 14);
		add(lblLneas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(299, 49, 219, 168);
		add(scrollPane_1);
		
		tableLines = new JTable(new String[][] {}, new String[] { "Art.", "Cantidad", "Precio", "Total" });
		scrollPane_1.setViewportView(tableLines);
		
		JButton btnAdd = new JButton("+");
		btnAdd.setBounds(517, 49, 47, 83);
		add(btnAdd);
		
		JButton btnRemove = new JButton("-");
		btnRemove.setBounds(517, 134, 47, 83);
		add(btnRemove);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(254, 243, 46, 14);
		add(lblTotal);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(299, 240, 265, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Guardar nuevo ticket");
		btnSave.setBounds(299, 271, 133, 23);
		add(btnSave);
		
		JButton btnLimpiarFormulario = new JButton("Limpiar formulario");
		btnLimpiarFormulario.setBounds(442, 271, 122, 23);
		add(btnLimpiarFormulario);

	}

}
