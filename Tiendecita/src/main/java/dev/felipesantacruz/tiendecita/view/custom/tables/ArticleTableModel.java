package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;

/**
 * <TableModel> para gestionar los datos del modelo {@link Article} en unión con un
 * <code>JTable</code>. Es una subclase de 
 * {@link NotEditableTableModelTemplate}.
 * @author Felipe Santa-Cruz
 * @version 1.0
 *
 */
public class ArticleTableModel extends NotEditableTableModelTemplate<Article>
{
	private static final long serialVersionUID = 1L;
	private static String[] columns = { "Artículo", "Precio", "Stock" };	
	
	/**
	 * Crea un <TableModel> para gestionar los datos el modelo {@link Article}.
	 * Será usado para rellenar las filas de una <code>JTable</code>.
	 */
	public ArticleTableModel()
	{
		super(columns);
	}
	
	/**
	 * Crea un <TableModel> para gestionar los datos el modelo {@link Article} y lo rellena
	 * con los datos encapsulados dentro de <code>articles</code>
	 * Será usado para rellenar las filas de una <code>JTable</code>.
	 * @param articles un <code>Iterator</code> con los datos que poblarán las filas de este
	 * <code>TableModel</code>
	 * @see Iterator
	 */
	public ArticleTableModel(Iterator<Article> articles)
	{
		super(columns, articles);
	}

	@Override
	protected void fillNewRow(Object[] row, Article article)
	{
		row[0] = article;
		row[1] = article.getPrice();
		row[2] = article.getStock();
	}

}
