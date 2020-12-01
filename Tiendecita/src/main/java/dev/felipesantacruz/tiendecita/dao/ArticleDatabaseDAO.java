package dev.felipesantacruz.tiendecita.dao;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dev.felipesantacruz.tiendecita.model.Article;

public class ArticleDatabaseDAO extends HibernateWirter implements ArticleDAO
{

	public ArticleDatabaseDAO(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}

	@Override
	public Collection<Article> findByDescription(final String description)
	{
		return findAll().stream().filter(articleContainsDescriptionLike(description)).collect(Collectors.toList());
	}

	private Predicate<? super Article> articleContainsDescriptionLike(final String description)
	{
		return article -> article.getDescription().toLowerCase().contains(description.toLowerCase());
	}

	@Override
	public Collection<Article> findAll()
	{
		Session session = getSessionFactory().openSession();
		Collection<Article> articles = session.createQuery("SELECT a FROM Article a", Article.class).getResultList();
		session.close();
		return articles;
	}

	@Override
	public void insert(Article article)
	{
		execute(s -> s.save(article));
	}

	@Override
	public void edit(Article article)
	{
		execute(s -> s.update(article));
	}

	@Override
	public void delete(Article article)
	{
		execute(s -> s.delete(article));
	}

}
