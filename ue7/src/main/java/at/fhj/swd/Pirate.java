package at.fhj.swd;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "ue07", name="Pirate")
public class Pirate extends Person
{

	private String crew;

	public Pirate(String crew)
	{
		setCrew(crew);
	}

	public Pirate(String name, String crew)
	{
		super(name);
		setCrew(crew);
	}

	public String getCrew()
	{
		return crew;
	}

	public void setCrew(String crew)
	{
		this.crew = crew;
	}

	@Override
	public String toString()
	{
		return "Pirate{" + "Crew='" + getCrew() + '\'' + '}';
	}
}
