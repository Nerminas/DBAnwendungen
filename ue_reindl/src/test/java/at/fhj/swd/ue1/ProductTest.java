package at.fhj.swd.ue1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ProductTest
{
	static final String persistanceUnitName = "products";
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static final int id = 1;
	static String name = "Schnitzelklopfer";
	static final Type type = Type.CODE128;


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


		Product testProduct = new Product(name,type);
		assertNotNull(testProduct);
		manager.persist(testProduct);
		transaction.commit();

		System.out.println("Created and persisted Entity " + testProduct);

	}

	@Test
	public void modify(){

		Product schnitzel = manager.find(Product.class, id);

		System.out.println("found Entity " + schnitzel);
		assertNotNull(schnitzel);
		transaction.begin();
		schnitzel.setName("keinSchnitzel");
		transaction.commit();
		assertEquals("keinSchnitzel",schnitzel.getName());
	}

	@Test
	public void remove(){
		Product testProduct = manager.find(Product.class,id);
		assertNotNull(testProduct);
		transaction.begin();
		manager.remove(testProduct);
		transaction.commit();

		testProduct = manager.find(Product.class,id);
		assertNull(testProduct);
	}


}
