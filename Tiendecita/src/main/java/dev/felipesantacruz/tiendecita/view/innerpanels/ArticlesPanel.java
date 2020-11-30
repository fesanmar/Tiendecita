package dev.felipesantacruz.tiendecita.view.innerpanels;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import dev.felipesantacruz.tiendecita.controllers.ArticleController;
import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.view.custom.ArticlesTable;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;

public class ArticlesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField tfSearch;
	private RefillableJTableTemplate<Article> tableArticles;
	private JTextField tfId;
	private JTextField tfDescription;
	private JFormattedTextField tfPrice;
	private ArticleController controller;
	private JSpinner spStock;
	private JButton btnNew;

	public ArticlesPanel(ArticleController articleController)
	{
		controller = articleController;
		setLayout(null);
		setUpComponents();
		clearForm();
		setUpListeners();

	}

	private void setUpComponents()
	{
		setUpLabels();
		setUpTextFields();
		setUpButtons();
		setUpSpinners();
		setUpTable();
	}

	private void setUpLabels()
	{
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(334, 48, 46, 14);
		add(lblId);

		JLabel lblArticle = new JLabel("Art\u00EDculo");
		lblArticle.setBounds(334, 99, 46, 14);
		add(lblArticle);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(334, 148, 46, 14);
		add(lblPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(334, 200, 46, 14);
		add(lblStock);
	}

	private void setUpTextFields()
	{
		tfSearch = new JTextField();
		tfSearch.setBounds(10, 12, 178, 20);
		add(tfSearch);
		tfSearch.setColumns(10);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(390, 45, 62, 20);
		add(tfId);
		tfId.setColumns(10);

		tfDescription = new JTextField();
		tfDescription.setBounds(390, 96, 155, 20);
		add(tfDescription);
		tfDescription.setColumns(10);

		tfPrice = new JFormattedTextField();
		tfPrice.setBounds(390, 145, 155, 20);
		add(tfPrice);
	}

	private void setUpButtons()
	{
		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBounds(209, 11, 89, 23);
		add(btnSearch);

		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(390, 255, 71, 23);
		add(btnSave);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(474, 255, 71, 23);
		add(btnEliminar);

		btnNew = new JButton("Nuevo");
		btnNew.setBounds(474, 44, 71, 23);
		add(btnNew);
	}

	private void setUpSpinners()
	{
		spStock = new JSpinner();
		spStock.setBounds(390, 197, 71, 20);
		add(spStock);
	}

	private void setUpTable()
	{
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 288, 233);
		add(scrollPane);
		tableArticles = new ArticlesTable();
		tableArticles.refill(controller.fetchAll());
		scrollPane.setViewportView(tableArticles);
	}

	private void setUpListeners()
	{
		setUpActionListeners();
		setUpSelectionListeners();
		
	}

	private void setUpActionListeners()
	{
		btnNew.addActionListener(e -> clearForm());
	}
	
	private void clearForm()
	{
		tfId.setText(String.valueOf(0));
		tfDescription.setText("");
		tfPrice.setValue(0.00);
		spStock.setValue(0);
		tableArticles.clearSelection();
		controller.setActiveArticle(new Article());
	}

	private void setUpSelectionListeners()
	{
		ListSelectionModel selectionModel = tableArticles.getSelectionModel();
		selectionModel.addListSelectionListener(this::manageTableSelection);
	}
	
	private void manageTableSelection(ListSelectionEvent e)
	{
		if(e.getValueIsAdjusting())
			selectNewArticle();	
	}

	private void selectNewArticle()
	{
		Article article = (Article) tableArticles.getValueAt(tableArticles.getSelectedRow(), 0);
		if (hasChanged(article))
			setNewArticle(article);
		fillForm();

	}

	private boolean hasChanged(Article article)
	{
		return controller.getActiveArticle() != article;
	}

	private void setNewArticle(Article article)
	{
		controller.setActiveArticle(article);
	}
	
	private void fillForm()
	{
		Article newArticle = controller.getActiveArticle();
		tfId.setText(String.valueOf(newArticle.getId()));
		tfDescription.setText(newArticle.getDescription());
		tfPrice.setValue(newArticle.getPrice());
		spStock.setValue(newArticle.getStock());
	}
}
