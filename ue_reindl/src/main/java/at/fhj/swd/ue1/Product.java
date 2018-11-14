package at.fhj.swd.ue1;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prd_id", nullable = false)
	private int id;
	@Enumerated(EnumType.STRING)
	@Column(name="prd_barcode_type")
	private Type barcodeType;
	@Column(name="prd_name")
	private String name;

	public Product(String name, Type barcodeType)
	{
		setId(id);
		setName(name);
		setBarcodeType(barcodeType);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Type getBarcodeType()
	{
		return barcodeType;
	}

	public void setBarcodeType(Type barcodeType)
	{
		this.barcodeType = barcodeType;
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
		Product product = (Product) o;
		return id == product.id && Objects.equals(name, product.name)
			&& barcodeType == product.barcodeType;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, barcodeType);
	}

	@Override
	public String toString()
	{
		return "Product{" + "id=" + id + ", name='" + name + '\'' + ", barcodeType=" + barcodeType
			+ '}';
	}
}
