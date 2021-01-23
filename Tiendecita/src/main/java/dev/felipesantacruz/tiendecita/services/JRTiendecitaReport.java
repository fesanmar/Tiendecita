package dev.felipesantacruz.tiendecita.services;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
			session.doWork(conn ->
			{
				try
				{
					JasperPrint print = JasperFillManager.fillReport(report, params, conn);
					File tempFile = File.createTempFile(report.getName(), ".pdf");
					JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(tempFile));
					Desktop.getDesktop().open(tempFile);
				} catch (JRException | IOException e)
				{
					throw new ReportConstructionException(e.toString());
				}
			});
		}
	}
}
