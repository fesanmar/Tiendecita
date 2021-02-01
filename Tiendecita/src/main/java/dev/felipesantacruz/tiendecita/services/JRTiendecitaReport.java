package dev.felipesantacruz.tiendecita.services;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.felipesantacruz.tiendecita.exceptions.ReportConstructionException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Generador de informes de <b>Tiendecita</b>.
 * @author Felipe Santa-Cruz
 *
 */
public class JRTiendecitaReport implements ReportService
{
	private SessionFactory sessionFactory;
	private JasperReport report;
	private Map<String, Object> params = new HashMap<>();

	/**
	 * Crea un generador de informes.
	 * @param sessionFactory la f�brica de sesiones que ser� usada para crear las sesiones
	 * con las que se realizar�n las consultas a la base de datos
	 * @param jasperFilepath la ruta al archivos <code>.jasper</code> que contiene la 
	 * plantilla del informe
	 * @throws URISyntaxException si <code>jasperFilepath</code> no puede ser parseado
	 * a una URI
	 * @throws JRException si no se puede cargar el archivo <code>.jasper</code>
	 */
	public JRTiendecitaReport(SessionFactory sessionFactory, String jasperFilepath)
			throws URISyntaxException, JRException
	{
		this.sessionFactory = sessionFactory;
		URL url = getClass().getClassLoader().getResource(jasperFilepath);
		File file = new File(url.toURI());
		report = (JasperReport) JRLoader.loadObject(file);
	}
	
	/**
	 * Establece los par�metros que se pasar�n al informe
	 * @param params pares clave valor con los nombres de los par�metro que se pasar�n al
	 * informe y sus valores
	 * @return el propio generador de informes, ya con los par�metros establecidos
	 */
	public JRTiendecitaReport setParams(Map<String, Object> params)
	{
		this.params = params;
		return this;
	}

	@Override
	public void createReport()
	{
		try (Session session = sessionFactory.openSession())
		{
			session.doWork(this::createReportAndDisplayIt);
		}
	}

	void createReportAndDisplayIt(Connection conn)
	{
		try
		{
			File tempFile = createReportFromConnection(conn);
			displayReportOnDefaultPdfViewer(tempFile);
		} catch (JRException | IOException e)
		{
			throw new ReportConstructionException(e.toString());
		}
	}

	private File createReportFromConnection(Connection conn) throws JRException, IOException, FileNotFoundException
	{
		JasperPrint print = JasperFillManager.fillReport(report, params, conn);
		File tempFile = File.createTempFile(report.getName(), ".pdf");
		JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(tempFile));
		return tempFile;
	}
	
	private void displayReportOnDefaultPdfViewer(File tempFile) throws IOException
	{
		Desktop.getDesktop().open(tempFile);
		tempFile.deleteOnExit();
	}
}
