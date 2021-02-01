package dev.felipesantacruz.tiendecita.view.custom.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dev.felipesantacruz.tiendecita.utils.DateFromToSubscriber;
import dev.felipesantacruz.tiendecita.utils.DateUtil;
import dev.felipesantacruz.tiendecita.view.custom.DateFormattedTextField;

public class DateFromToDialogAsker extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField tfFrom;
	private JFormattedTextField tfTo;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private DateFromToSubscriber publisher;


	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public DateFromToDialogAsker(JFrame f, DateFromToSubscriber publisher) throws ParseException
	{
		super(f);
		this.publisher = publisher;
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

		setUpButtonPane();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	void setUpButtonPane()
	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		setUpAcceptanceButton(buttonPane);
		setUpCancelButton(buttonPane);
	}


	void setUpAcceptanceButton(JPanel buttonPane)
	{
		JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> publishUserDataIfValid());
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	void publishUserDataIfValid()
	{
		if (userDataIsValid())
			publishUserData();
		else
			showReportErrorMessasge("Las fechas introducidas son incorrectas.");
	}
	
	private boolean userDataIsValid()
	{
		try
		{
			dateFrom = DateUtil.toLocalDate(tfFrom.getText());
			dateTo = DateUtil.toLocalDate(tfTo.getText());
			return true;
		} catch (DateTimeParseException e)
		{
			return false;
		}
	}

	void publishUserData()
	{
		if (dateFrom.isBefore(dateTo))
			publishUserDataAndDispose();
		else
			showReportErrorMessasge("La fecha 'desde' debe ser anterior a la fecha 'hasta'.");
	}

	void publishUserDataAndDispose()
	{
		publisher.update(
				Timestamp.valueOf(LocalDateTime.of(dateFrom, LocalTime.MIN)),
				Timestamp.valueOf(LocalDateTime.of(dateTo, LocalTime.MAX)));
		dispose();
	}
	
	void showReportErrorMessasge(String msg)
	{
		JOptionPane.showMessageDialog(this,
			    msg,
			    "Error al generar el informe",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	void setUpCancelButton(JPanel buttonPane)
	{
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(e -> dispose());
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
