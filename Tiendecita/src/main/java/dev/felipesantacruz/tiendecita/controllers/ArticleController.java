package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.dao.ArticleDAO;
import dev.felipesantacruz.tiendecita.model.Article;

public class ArticleController implements Controller<Article>
{
	private ArticleDAO dao;
	private Article activeArticle;
	
	public ArticleController(ArticleDAO dao)
	{
		this.dao = dao;
	}
	
	public Iterator<Article> fetchArticlesWithDescription(String description)
	{
		return dao.findByDescription(description).iterator();
	}
	
	@Override
	public void setActiveItem(Article a)
	{
		activeArticle = a;
	}
	
	@Override
	public Article getActiveItem()
	{
		return activeArticle; 
	}
	
	@Override
	public Iterator<Article> fetchAll()
	{
		return dao.findAll().iterator();
	}
	
	@Override
	public void insertActiveItem()
	{
		dao.insert(activeArticle);
	}
	
	public void updateActiveItem()
	{
		dao.edit(activeArticle);
	}
	
	public void deleteActiveItem()
	{
		dao.delete(activeArticle);
	}
}
