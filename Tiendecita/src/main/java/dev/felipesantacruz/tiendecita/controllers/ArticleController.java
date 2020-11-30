package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.dao.ArticleDAO;
import dev.felipesantacruz.tiendecita.model.Article;

public class ArticleController
{
	private ArticleDAO dao;
	private Article activeArticle;
	
	public ArticleController(ArticleDAO dao)
	{
		this.dao = dao;
	}
	
	public void setActiveArticle(Article a)
	{
		activeArticle = a;
	}
	
	public Article getActiveArticle()
	{
		return activeArticle; 
	}
	
	public Iterator<Article> fetchAll()
	{
		return dao.findAll().iterator();
	}
	
	public void insertActiveArticle()
	{
		dao.insert(activeArticle);
	}
	
	public void updateActiveArticle()
	{
		dao.edit(activeArticle);
	}
}
