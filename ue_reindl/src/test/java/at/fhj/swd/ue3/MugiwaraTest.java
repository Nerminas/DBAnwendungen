package at.fhj.swd.ue3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import at.fhj.swd.ue1.Product;
import at.fhj.swd.ue1.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//@org.junit.FixMethodOrder(MethodSorters.DEFAULT)
public class MugiwaraTest
{
	static final String persistanceUnitName = "mugiwara";
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;

	static String nameLuffy = "Ruffy";
	static String nameZoro = "Zoro";
	static final HakiType typeLuffy = HakiType.EROBERER;
	static final HakiType typeZoro = HakiType.RUESTUNG;


	@BeforeClass
	public static void setup (){
		factory = Persistence.createEntityManagerFactory(persistanceUnitName);
		assertNotNull(factory);
		manager = factory.createEntityManager();
		assertNotNull(manager);

		transaction = manager.getTransaction();
	}

	@AfterClass
	public static void teardown(){
		if (manager == null){
			return;
		}
		manager.close();
		factory.close();
	}

	@Test
	public void create(){
		transaction.begin();
		Mugiwara ruffy = new Mugiwara(nameLuffy, typeLuffy);
		manager.persist(ruffy);
		assertNotNull(ruffy);
		transaction.commit();
		System.out.println("Created and persisted Entity " + ruffy);

		transaction.begin();
		Mugiwara zoro = new Mugiwara(nameZoro, typeZoro);
		assertNotNull(zoro);
		manager.persist(zoro);
		transaction.commit();
		System.out.println("Created and persisted Entity " + zoro);

	}

	@Test
	public void modify(){

		Mugiwara ruffy = manager.find(Mugiwara.class, 1);
		Mugiwara zoro = manager.find(Mugiwara.class, 2);

		System.out.println("found Entity " + ruffy);
		System.out.println("found Entity " + zoro);
		assertNotNull(ruffy);
		assertNotNull(zoro);

		transaction.begin();
		ruffy.setName("Nami");
		transaction.commit();
		assertEquals("Nami",ruffy.getName());
	}

	@Test
	public void verify()
	{
		List<Mugiwara> prds = manager.createQuery("SELECT e FROM Mugiwara e", Mugiwara.class).getResultList();
		assertEquals(1, prds.size());
	}

	@Test
	public void remove(){
		Mugiwara ruffy = manager.find(Mugiwara.class,1);
		assertNotNull(ruffy);
		transaction.begin();
		manager.remove(ruffy);
		transaction.commit();

		ruffy = manager.find(Mugiwara.class,1);
		assertNull(ruffy);
	}


}
