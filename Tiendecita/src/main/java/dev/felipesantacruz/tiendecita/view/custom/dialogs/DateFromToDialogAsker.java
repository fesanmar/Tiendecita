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

/**
 * Diálogo en el que se preguntará al usuario que introduzca los datos de la <i>fecha desde</i> 
 * y la <i>fecha hasta</i>.
 * @author Felipe Santa-Cruz
 * @version 1.0
 */
public class DateFromToDialogAsker extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField tfFrom;
	private JFormattedTextField tfTo;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private transient DateFromToSubscriber publisher;


	
	/**
	 * Crea el dálogo que recoge los datos de la <i>fecha desde</i> y la <i>fecha hasta</i>.
	 * @param f la ventana padre de este diálogo
	 * @param subscriber el susbcritor que recibirá la <i>fecha desde</i> y la <i>fecha hasta</i>
	 * cuando sean establecidas por el usuario
	 * @throws ParseException si ocurre un error de parseo inesperado durante la creación de los
	 * campos tipo fecha.
	 * @see dev.felipesantacruz.tiendecita.view.custom.DateFormattedTextField
	 */
	public DateFromToDialogAsker(JFrame f, DateFromToSubscriber subscriber) throws ParseException
	{
		super(f);
		this.publisher = subscriber;
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

	private void setUpButtonPane()
	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		setUpAcceptanceButton(buttonPane);
		setUpCancelButton(buttonPane);
	}


	private void setUpAcceptanceButton(JPanel buttonPane)
	{
		JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> publishUserDataIfValid());
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}
	
	private void publishUserDataIfValid()
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

	private void publishUserData()
	{
		if (dateFrom.isBefore(dateTo))
			publishUserDataAndDispose();
		else
			showReportErrorMessasge("La fecha 'desde' debe ser anterior a la fecha 'hasta'.");
	}

	private void publishUserDataAndDispose()
	{
		publisher.update(
				Timestamp.valueOf(LocalDateTime.of(dateFrom, LocalTime.MIN)),
				Timestamp.valueOf(LocalDateTime.of(dateTo, LocalTime.MAX)));
		dispose();
	}
	
	private void showReportErrorMessasge(String msg)
	{
		JOptionPane.showMessageDialog(this,
			    msg,
			    "Error al generar el informe",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void setUpCancelButton(JPanel buttonPane)
	{
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(e -> dispose());
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
