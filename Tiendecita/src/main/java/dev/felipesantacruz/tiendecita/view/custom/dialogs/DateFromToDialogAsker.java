package dev.felipesantacruz.tiendecita.view.custom.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dev.felipesantacruz.tiendecita.view.custom.DateFormattedTextField;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class DateFromToDialogAsker extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField tfFrom;
	private JFormattedTextField tfTo;
	private JButton okButton;
	private JButton cancelButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			DateFromToDialogAsker dialog = new DateFromToDialogAsker();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public DateFromToDialogAsker() throws ParseException
	{
		setTitle("Consulta tickets");
		setBounds(100, 100, 240, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFrom = new JLabel("Desde");
		lblFrom.setBounds(10, 28, 46, 14);
		contentPanel.add(lblFrom);
		
		tfFrom = new DateFormattedTextField(10);
		tfFrom.setBounds(77, 25, 137, 20);
		contentPanel.add(tfFrom);
		tfFrom.setColumns(10);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(10, 59, 46, 14);
		contentPanel.add(lblHasta);
		
		tfTo = new DateFormattedTextField(10);
		tfTo.setBounds(77, 56, 137, 20);
		contentPanel.add(tfTo);
		tfTo.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
