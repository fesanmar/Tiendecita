package dev.felipesantacruz.tiendecita.dao;

import java.util.Collection;

import dev.felipesantacruz.tiendecita.model.Article;

public interface ArticleDAO
{
	Article findById(int id);
	
	Collection<Article> findAll();
	
	void insert(Article article);
	
	void edit(Article article);
	
	void delete(Article article);
}
