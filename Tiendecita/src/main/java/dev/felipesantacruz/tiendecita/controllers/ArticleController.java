package dev.felipesantacruz.tiendecita.controllers;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.dao.ArticleDAO;
import dev.felipesantacruz.tiendecita.model.Article;

/**
 * Implementación de la interfaz {@link Controller} para el model de datos {@link Article}
 * @author Felipe Santa-Cruz
 *
 */
public class ArticleController implements Controller<Article>
{
	private ArticleDAO dao;
	private Article activeArticle;
	
	/**
	 * Crea el controlador que administra objetos de tipo {@link Article}. Utiliza para ello
	 * un objeto del tipo {@link ArticleDAO}, en el que se definen las operaciones de recuperación
	 * y persistencia a realizar con el modelo de datos <code>Article</code>.
	 * @param dao componente en el se define cómo se relizarán las operaciones de recuperación
	 * y persistencia para los objetos de la clase <code>Article</code> gestionados por este
	 * controlador.
	 */
	public ArticleController(ArticleDAO dao)
	{
		this.dao = dao;
	}
	
	/**
	 * Devuelve todos los artículos cuya descripción coincida con el argumento <code>description</code>.
	 * Los ítems son devueltos dentro de un <code>Iterator</code>.
	 * @param description la cadena que se usará para realizar la búsqueda.
	 * @return el <code>Iterator</code> de artículos que coincidan con la cadena <code>description</code>
	 */
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
	
	/**
	 * Actualiza el elemento activo en la base de datos.
	 * El elemento activo se fija usando el método {@link #setActiveItem(Article)}
	 */
	public void updateActiveItem()
	{
		dao.edit(activeArticle);
	}
	
	/**
	 * Elimina el elemento activo en la base de datos.
	 * El elemento activo se fija usando el método {@link #setActiveItem(Article)}
	 */
	public void deleteActiveItem()
	{
		dao.delete(activeArticle);
	}
}
