package dev.felipesantacruz.tiendecita.view.custom.tables;

import java.util.Iterator;

import dev.felipesantacruz.tiendecita.model.Article;
import dev.felipesantacruz.tiendecita.view.custom.NotEditableTableModelTemplate;

public class ArticleTableModel extends NotEditableTableModelTemplate<Article>
{
	private static final long serialVersionUID = 1L;
	private static String[] columns = { "Artículo", "Precio", "Stock" };	
	
	public ArticleTableModel()
	{
		super(columns);
	}
	
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
