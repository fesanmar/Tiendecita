package dev.felipesantacruz.tiendecita.view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dev.felipesantacruz.tiendecita.controllers.ArticleController;
import dev.felipesantacruz.tiendecita.controllers.TicketController;
import dev.felipesantacruz.tiendecita.dao.ArticleDAO;
import dev.felipesantacruz.tiendecita.dao.ArticleDatabaseDAO;
import dev.felipesantacruz.tiendecita.dao.TicketDAO;
import dev.felipesantacruz.tiendecita.dao.TicketDatabaseDAO;
import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.services.JRTiendecitaReport;
import dev.felipesantacruz.tiendecita.services.ReportService;
import dev.felipesantacruz.tiendecita.utils.DateFromToSubscriber;
import dev.felipesantacruz.tiendecita.view.custom.dialogs.DateFromToDialogAsker;
import dev.felipesantacruz.tiendecita.view.innerpanels.ArticlesPanel;
import dev.felipesantacruz.tiendecita.view.innerpanels.TicketsPanel;
import net.sf.jasperreports.engine.JRException;


public class HomeView extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArticlesPanel articlesPanel;
	private TicketsPanel ticketsPanel;
	
	private JMenuItem mntmManageArticles;
	private JMenuItem mntmManageTickets;
	private JMenuItem mntmArticlesList;
	private JMenuItem mntmTicketsByDate;
	
	private static SessionFactory sessionFactory;
	private static ArticleController articleController;
	private static TicketController ticketController;

	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException
	{
		sessionFactory = new Configuration().addAnnotatedClass(Article.class).configure().buildSessionFactory();
		ArticleDAO articleDAO = new ArticleDatabaseDAO(sessionFactory);
		TicketDAO ticketDAO = new TicketDatabaseDAO(sessionFactory);
		articleController = new ArticleController(articleDAO);
		ticketController = new TicketController(ticketDAO);
		
		setLookAndFeel();
		EventQueue.invokeLater( () ->
		{
			try
			{
				HomeView frame = new HomeView();
				frame.setVisible(true);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}
	
	private static void setLookAndFeel() throws ClassNotFoundException
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			try
			{
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public HomeView()
	{
		setTitle("Tiendecita");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 639, 418);
		addWindowListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArticles = new JMenu("Artículos");
		menuBar.add(mnArticles);
		
		mntmManageArticles = new JMenuItem("Gestionar artículos");
		
		mnArticles.add(mntmManageArticles);
		
		JMenu mnTickets = new JMenu("Tickets");
		menuBar.add(mnTickets);
		
		mntmManageTickets = new JMenuItem("Gestionar tickets");
		mnTickets.add(mntmManageTickets);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		articlesPanel = new ArticlesPanel(articleController);
		articlesPanel.setName("ArticlesPanel");
		contentPane.add(articlesPanel, articlesPanel.getName());
		ticketsPanel = new TicketsPanel(ticketController, articleController);
		ticketsPanel.setName("Tickets");
		contentPane.add(ticketsPanel, ticketsPanel.getName());
		
		
		JMenu mnReports = new JMenu("Informes");
		menuBar.add(mnReports);
		
		mntmArticlesList = new JMenuItem("Listado artículos");
		mnReports.add(mntmArticlesList);
		
		mntmTicketsByDate = new JMenuItem("Tickets por fecha");
		mnReports.add(mntmTicketsByDate);

		addActionListeners();
	}

	private void addActionListeners()
	{
		mntmManageArticles.addActionListener(e -> showCard(articlesPanel.getName()));
		mntmManageTickets.addActionListener(e -> showCard(ticketsPanel.getName()));
		mntmArticlesList.addActionListener(e -> displayReport("reports/articles_report.jasper", null));
		mntmTicketsByDate.addActionListener(e -> 
		{
			try
			{
				JDialog dialog = new DateFromToDialogAsker(this, this::update);
				dialog.setVisible(true);
			} catch (ParseException e1)
			{
				e1.printStackTrace();
			}
		});
			
	}
	
	private void showCard(String cardName)
	{
		((CardLayout) contentPane.getLayout()).show(contentPane, cardName);
	}
	
	void displayReport(String jasperCompilationPath, Map<String, Object> params)
	{
		try
		{
			ReportService reportService = 
					new JRTiendecitaReport(sessionFactory, jasperCompilationPath)
					.setParams(params);
			reportService.createReport();
			
		} catch (URISyntaxException | JRException | RuntimeException e1)
		{
			JOptionPane.showMessageDialog(this,
				    "Ocurrió un error al crear el informe",
				    "Error al generar el informe",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void update(Timestamp from, Timestamp to)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("dateFrom", from);
		params.put("dateTo", to);
		displayReport("reports/tickets_report.jasper", params);
	}
	
	@Override
	public void windowOpened(WindowEvent e) { }

	@Override
	public void windowClosing(WindowEvent e) 
	{
		int option = JOptionPane.showOptionDialog(
				this, "¿Seguro que quiere abandonar el programa?", 
				"Tiendecita se cerrará", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == 0)
			closeResourcesAndLeave();
	}

	@Override
	public void windowClosed(WindowEvent e) { }

	private void closeResourcesAndLeave()
	{
		sessionFactory.close();
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowActivated(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }

}
