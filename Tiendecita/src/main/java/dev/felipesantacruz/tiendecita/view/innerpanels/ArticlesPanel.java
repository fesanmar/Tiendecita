package dev.felipesantacruz.tiendecita.view.innerpanels;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;

public class ArticlesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField tfSearch;
	private JTable tableArticles;
	private JTextField tfId;
	private JTextField textField;

	public ArticlesPanel()
	{
		setLayout(null);

		tfSearch = new JTextField();
		tfSearch.setBounds(10, 12, 178, 20);
		add(tfSearch);
		tfSearch.setColumns(10);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(209, 11, 89, 23);
		add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 288, 233);
		add(scrollPane);

		tableArticles = new JTable(new String[][] {}, new String[] { "Artículo", "Precio", "Stock" });
		scrollPane.setViewportView(tableArticles);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(334, 48, 46, 14);
		add(lblId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(390, 45, 62, 20);
		add(tfId);
		tfId.setColumns(10);

		JLabel lblArticle = new JLabel("Art\u00EDculo");
		lblArticle.setBounds(334, 99, 46, 14);
		add(lblArticle);

		textField = new JTextField();
		textField.setBounds(390, 96, 155, 20);
		add(textField);
		textField.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(334, 148, 46, 14);
		add(lblPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(334, 200, 46, 14);
		add(lblStock);

		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(390, 255, 71, 23);
		add(btnSave);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(474, 255, 71, 23);
		add(btnEliminar);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(390, 197, 71, 20);
		add(spinner);

		JFormattedTextField tfPrice = new JFormattedTextField();
		tfPrice.setBounds(390, 145, 155, 20);
		add(tfPrice);

		JButton btnNew = new JButton("Nuevo");
		btnNew.setBounds(474, 44, 71, 23);
		add(btnNew);

	}
}
