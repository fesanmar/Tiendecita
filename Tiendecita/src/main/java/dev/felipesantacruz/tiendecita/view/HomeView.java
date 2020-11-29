package dev.felipesantacruz.tiendecita.view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.model.Ticket;
import dev.felipesantacruz.tiendecita.model.TicketLine;
import dev.felipesantacruz.tiendecita.view.innerpanels.ArticlesPanel;
import dev.felipesantacruz.tiendecita.view.innerpanels.TicketsPanel;


public class HomeView extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArticlesPanel articlesPanel;
	private TicketsPanel ticketsPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
//		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Article.class).configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
//		
//		Article article = new Article();
//		article.setDescription("Un lápiz");
//		article.setPrice(new BigDecimal(0.25));
//		session.save(article);
//		
//		Ticket ticket = new Ticket();
//		ticket.setAmount(new BigDecimal(0.25));
//		
//		
//		session.save(ticket);
//		session.flush();
//		
//		TicketLine line = new TicketLine();
//		line.setArticle(article);
//		line.setQuantity(1);
//		ticket.addLine(line);
//		
//		session.save(ticket);
//		
//		tx.commit();
//
//		session.close();
//		sessionFactory.close();
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					HomeView frame = new HomeView();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeView()
	{
		setTitle("Tiendecita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 418);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArticles = new JMenu("Artículos");
		menuBar.add(mnArticles);
		
		JMenuItem mntmManageArticles = new JMenuItem("Gestionar artículos");
		
		mnArticles.add(mntmManageArticles);
		
		JMenu mnTickets = new JMenu("Tickets");
		menuBar.add(mnTickets);
		
		JMenuItem mntmManageTickets = new JMenuItem("Gestionar tickets");
		mnTickets.add(mntmManageTickets);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		articlesPanel = new ArticlesPanel();
		articlesPanel.setName("ArticlesPanel");
		contentPane.add(articlesPanel, articlesPanel.getName());
		ticketsPanel = new TicketsPanel();
		ticketsPanel.setName("Tickets");
		contentPane.add(ticketsPanel, ticketsPanel.getName());
		
		addActionListeners(mntmManageArticles, mntmManageTickets);
	}

	private void addActionListeners(JMenuItem mntmManageArticles, JMenuItem mntmManageTickets)
	{
		mntmManageArticles.addActionListener(e -> showCard(articlesPanel.getName()));
		mntmManageTickets.addActionListener(e -> showCard(ticketsPanel.getName()));
	}
	
	private void showCard(String cardName)
	{
		((CardLayout) contentPane.getLayout()).show(contentPane, cardName);
	}

}
