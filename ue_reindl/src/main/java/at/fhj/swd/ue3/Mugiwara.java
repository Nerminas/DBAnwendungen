package at.fhj.swd.ue3;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MUGIWARA")
public class Mugiwara
{
	@SequenceGenerator(name = "sthIdGen", sequenceName = "sth_seq", initialValue = 1, allocationSize = 1)
	@Id
	@Column(name = "sth_id", nullable = false)
	@GeneratedValue(generator="sthIdGen")
	private int id;
	@Enumerated(EnumType.STRING)
	@Column(name="sth_haki_type")
	private HakiType hakiType;
	@Column(name="sth_name")
	private String name;

	public Mugiwara(String name, HakiType hakiType)
	{
		setId(id);
		setName(name);
		setHakiType(hakiType);
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

	public HakiType getHakiType()
	{
		return hakiType;
	}

	public void setHakiType(HakiType hakiType)
	{
		this.hakiType = hakiType;
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
		Mugiwara mugiwara = (Mugiwara) o;
		return id == mugiwara.id && Objects.equals(name, mugiwara.name)
			&& hakiType == mugiwara.hakiType;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, hakiType);
	}

	@Override
	public String toString()
	{
		return "Mugiwara{" + "id=" + id + ", name='" + name + '\'' + ", barcodeHakiType=" + hakiType
			+ '}';
	}
}
