package dev.felipesantacruz.tiendecita.dao;

import java.util.Collection;

import dev.felipesantacruz.tiendecita.model.Article;

/**
 * Representa el conjunto de operaciones de consulta y persistencia 
 * que se pueden realizar sobre el modelo de datos {@link Article}
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
public interface ArticleDAO
{
	/**
	 * Devuelve todos los art�uclos cuya descripci�n coincida con <code>description</code>
	 * @param description la cadena de texto utilizada para realizar la b�squeda de art�culos
	 * @return un objeto del tipo <code>Collection</code> con tods los art�culos
	 * cuya descripci�n coincida con <code>description</code>
	 * @see Collection
	 */
	Collection<Article> findByDescription(String description);
	
	/**
	 * Devuelve todos los elementos disponibles en el repositorio de datos, del tipo {@link Article}.
	 * @return un objeto de tipo <code>Collection</code> con todos los objetos
	 * del tipo <code>Article</code> disponible en el repositorio de datos 
	 */
	Collection<Article> findAll();
	
	/**
	 * Inserta un nuevo art�culo en el repositorio de datos.
	 * @param article el elemento que se quiere persistir
	 */
	void insert(Article article);
	
	/**
	 * Actualiza el elemento <code>article</code> en el respositorio de datos. 
	 * @param article el elemento con los datos actualizados que se usar� para
	 * actualizar el elemento de ind�ntico <code>id</code> en el respositorio de datos.
	 */
	void edit(Article article);
	
	/**
	 * Elimina el elemento <code>article</code> del respositorio de datos. 
	 * @param article el elemento del respositorio de datos.
	 */
	void delete(Article article);
}
