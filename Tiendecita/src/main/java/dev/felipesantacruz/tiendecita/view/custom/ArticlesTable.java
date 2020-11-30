package dev.felipesantacruz.tiendecita.view.custom;

import java.util.Iterator;

import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dev.felipesantacruz.tiendecita.model.Article;

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
		// TODO Auto-generated method stub

	}

}
