package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;
import dev.felipesantacruz.tiendecita.view.custom.RefillableJTableTemplate;

/**
 * Tabla que contendrá los datos del modelo {@link Article}. Es una subclase
 * de la clase abstracta {@link RefillableJTableTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0 
 */
public class ArticlesTable extends RefillableJTableTemplate<Article>
{
	private static final long serialVersionUID = 1L;

	@Override
	protected NotEditableTableModelTemplate<Article> createTableModel()
	{
		return new ArticleTableModel();
	}

	@Override
	protected TableModel createTableModel(Iterator<Article> iterator)
	{
		return new ArticleTableModel(iterator);
	}

	@Override
	protected void setWidthForColumnNumber(int columnIndex, TableColumn column) 
	{ 
		// Se deja la anchura por defecto de las columnas
	}

}
