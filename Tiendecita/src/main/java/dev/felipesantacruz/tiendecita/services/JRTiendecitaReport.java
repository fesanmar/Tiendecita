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

public class JRTiendecitaReport implements ReportService
{
	private SessionFactory sessionFactory;
	private JasperReport report;
	private Map<String, Object> params = new HashMap<>();

	public JRTiendecitaReport(SessionFactory sessionFactory, String jasperFilepath)
			throws URISyntaxException, JRException
	{
		this.sessionFactory = sessionFactory;
		URL url = getClass().getClassLoader().getResource(jasperFilepath);
		File file = new File(url.toURI());
		report = (JasperReport) JRLoader.loadObject(file);
	}
	
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
