package dev.felipesantacruz.tiendecita.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa un artículo a la venta de nuestra <b>Tiendecita</b>.
 * @author Felipe Santa-Cruz
 * @version 1.0
 * 
 */
@Entity
@Table(name = "article")
public class Article
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	private BigDecimal price;
	
	private int stock;

	/**
	 * Crear un artículo sin sin información. 
	 */
	public Article() 
	{
		// Constructor vacío, requerido por Hibernate
	}

	/**
	 * Devuelve el <code>id</code> del artículo.
	 * @return el identificador único, o <code>id</code> del artículo
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Establece el <code>id</code> como identificador único de este artículo.
	 * @param id el número de que será establecido como nuevo identificador único del
	 * artículo
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Devuelve la descripción del artículo.
	 * @return una descripción textual del artículo
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Establece la descripción textual del artículo.
	 * @param description el texto que se establecerá como nueva descripción del artículo.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Devuelve el precio del artículo.
	 * @return un <code>BigDecimal</code> que representa el precio del artículo.
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/**
	 * Establece el precio del artículo.
	 * @param price un <code>BigDecimal</code> con el nuevo precio del artículo
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/**
	 * Devuelve el númeo de items de este artículo que hay en stock
	 * @return un número entero que representa el stock de este artículo.
	 */
	public int getStock()
	{
		return stock;
	}

	/**
	 * Establece el nuevo stock de ítems para este artículo.
	 * @param stock el número de elementos en stock para este artículo.
	 */
	public void setStock(int stock)
	{
		this.stock = stock;
	}
	
	@Override
	public String toString()
	{
		return description;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
