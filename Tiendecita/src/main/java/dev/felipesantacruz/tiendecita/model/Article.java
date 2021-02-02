package dev.felipesantacruz.tiendecita.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa un art�culo a la venta de nuestra <b>Tiendecita</b>.
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
	 * Crear un art�culo sin sin informaci�n. 
	 */
	public Article() 
	{
		// Constructor vac�o, requerido por Hibernate
	}

	/**
	 * Devuelve el <code>id</code> del art�culo.
	 * @return el identificador �nico, o <code>id</code> del art�culo
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Establece el <code>id</code> como identificador �nico de este art�culo.
	 * @param id el n�mero de que ser� establecido como nuevo identificador �nico del
	 * art�culo
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Devuelve la descripci�n del art�culo.
	 * @return una descripci�n textual del art�culo
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Establece la descripci�n textual del art�culo.
	 * @param description el texto que se establecer� como nueva descripci�n del art�culo.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * Devuelve el precio del art�culo.
	 * @return un <code>BigDecimal</code> que representa el precio del art�culo.
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/**
	 * Establece el precio del art�culo.
	 * @param price un <code>BigDecimal</code> con el nuevo precio del art�culo
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/**
	 * Devuelve el n�meo de items de este art�culo que hay en stock
	 * @return un n�mero entero que representa el stock de este art�culo.
	 */
	public int getStock()
	{
		return stock;
	}

	/**
	 * Establece el nuevo stock de �tems para este art�culo.
	 * @param stock el n�mero de elementos en stock para este art�culo.
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
