package dev.felipesantacruz.tiendecita.view.innerpanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import dev.felipesantacruz.tiendecita.controllers.TicketController;
import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.model.TicketLine;

public class TicketLineDialog extends JDialog implements DialogAcceptedSubject
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final TicketController controller;
	private final TicketLine line = new TicketLine();
	private JComboBox<Article> cbArticles;
	private JSpinner spQuantity;
	private DialogAcceptedObserver observer = () -> {};


	/**
	 * Create the dialog.
	 */
	public TicketLineDialog(TicketController controller, Iterator<Article> iterator)
	{
		this.controller = controller;
		setBounds(100, 100, 283, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel lblArticles = new JLabel("Art\u00EDculos");
		lblArticles.setBounds(10, 22, 46, 14);
		contentPanel.add(lblArticles);
		
		spQuantity = new JSpinner();
		spQuantity.setBounds(83, 55, 153, 20);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spQuantity.getEditor()).getTextField();
		NumberFormatter formatter = (NumberFormatter) txt.getFormatter();
		formatter.setAllowsInvalid(false);
		contentPanel.add(spQuantity);
		
		JLabel lblQuantity = new JLabel("Cantidad");
		lblQuantity.setBounds(10, 58, 46, 14);
		contentPanel.add(lblQuantity);
		
		cbArticles = new JComboBox<>(createArrayFromIterator(iterator));
		cbArticles.setBounds(83, 19, 153, 20);
		contentPanel.add(cbArticles);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(e -> addNewLineToActiveTicket());
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e -> dispose());
			}
		}
	}
	
	private Article[] createArrayFromIterator(Iterator<Article> iterator)
	{
		List<Article> articlesList = new ArrayList<>();
		while (iterator.hasNext())
			articlesList.add(iterator.next());
		return articlesList.toArray(new Article[articlesList.size()]);
	}
	
	private void addNewLineToActiveTicket()
	{
		Ticket activeTicket = controller.getActiveItem();
		line.setArticle((Article) cbArticles.getSelectedItem());
		line.setQuantity((int) spQuantity.getValue());
		activeTicket.addLine(line);
		notifyObserver();
		dispose();
	}

	@Override
	public void setDialogAcceptedObserver(DialogAcceptedObserver o)
	{
		observer = o;
	}

	@Override
	public void notifyObserver()
	{
		observer.update();
	}
}
