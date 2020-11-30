package dev.felipesantacruz.tiendecita.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dev.felipesantacruz.tiendecita.model.Article;

public class ArticleDatabaseDAO implements ArticleDAO
{
	
	private SessionFactory sessionFactory;
	
	public ArticleDatabaseDAO(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public Article findById(int id)
	{
		Session session = sessionFactory.openSession();
		Article article = session.find(Article.class, id);
		session.close();
		return article;
	}

	@Override
	public Collection<Article> findAll()
	{
		Session session = sessionFactory.openSession();
		Collection<Article> articles = session.createQuery("SELECT a FROM Article a", Article.class).getResultList();
		session.close();
		return articles;
	}

	@Override
	public void insert(Article article)
	{
		execute(s -> s.save(article));
	}


	private void execute(CRUDOperation operation)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		operation.executeWith(session);
		session.flush();
		transaction.commit();
		session.close();
	}

	@Override
	public void edit(Article article)
	{
		execute(s -> s.update(article));
	}

	@Override
	public void delete(Article article)
	{
		// TODO Auto-generated method stub

	}

}
