package main.java.at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products", schema = "public", catalog = "swd_ws18_13")
public class ProductsEntity
{
	private String name;
	private int id;

	@Basic
	@Column(name = "name")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Id
	@Column(name = "id")
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}

		ProductsEntity that = (ProductsEntity) o;

		if(id != that.id)
		{
			return false;
		}
		if(name != null ? !name.equals(that.name) : that.name != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + id;
		return result;
	}
}
