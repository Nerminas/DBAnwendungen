package main.java.at.fhj.swd;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "storage", schema = "public", catalog = "swd_ws18_13")
public class StorageEntity
{
	private int id;
	private Integer location;
	private Integer amount;

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

	@Basic
	@Column(name = "location")
	public Integer getLocation()
	{
		return location;
	}

	public void setLocation(Integer location)
	{
		this.location = location;
	}

	@Basic
	@Column(name = "amount")
	public Integer getAmount()
	{
		return amount;
	}

	public void setAmount(Integer amount)
	{
		this.amount = amount;
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

		StorageEntity that = (StorageEntity) o;

		if(id != that.id)
		{
			return false;
		}
		if(location != null ? !location.equals(that.location) : that.location != null)
		{
			return false;
		}
		if(amount != null ? !amount.equals(that.amount) : that.amount != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id;
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (amount != null ? amount.hashCode() : 0);
		return result;
	}
}
