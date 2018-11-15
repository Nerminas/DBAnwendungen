package at.fhj.swd;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema="ue07", name="Marine")
public class Marine extends Person
{
	private String base;

	public Marine(String base)
	{
		setBase(base);
	}

	public Marine(String name, String base)
	{
		super(name);
		setBase(base);
	}

	public String getBase()
	{
		return base;
	}

	public void setBase(String base)
	{
		this.base = base;
	}

	@Override
	public String toString()
	{
		return "Marine{" + "base='" + getBase() + '\'' + '}';
	}
}
