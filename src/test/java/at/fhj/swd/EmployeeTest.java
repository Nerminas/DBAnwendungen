/*
 * project    company
 * subproject simplest
 */

package test.java.at.fhj.swd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

import main.java.at.fhj.swd.ProductsEntity;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "products";

    static final int id = 1;
    static final String name = "David";


    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);

        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null)
            return;

        manager.close();
        factory.close();
    }


    @Test
    public void create() {
        transaction.begin();
        ProductsEntity testProduct = new ProductsEntity();
        testProduct.setId(id);
        testProduct.setName(name);
        assertNotNull(testProduct);
        manager.persist(testProduct);
        transaction.commit();

        System.out.println("Created and Persisted " + testProduct);

    }

    @Test
    public void modify() {
        ProductsEntity testProduct = manager.find(ProductsEntity.class, id);
        assertNotNull(testProduct);
        System.out.println("Found " + testProduct);

        transaction.begin();
        //testProduct.raiseSalary(salaryRaise);
        transaction.commit();

        //#if STRICT
        //start from scratch - this ensures that testProduct is fetched from the DB :
        //teardown ();
        //setup    ();
        //#endif

        testProduct = manager.find(ProductsEntity.class, id);

        //assertEquals(salary + salaryRaise, (int) testProduct.getSalary());
        System.out.println("Updated " + testProduct);
    }

    @Test
    public void remove() {
        ProductsEntity testProduct = manager.find(ProductsEntity.class, id);
        assertNotNull(testProduct);

        transaction.begin();
        manager.remove(testProduct);
        transaction.commit();

        testProduct = manager.find(ProductsEntity.class, id);
        assertNull(testProduct);

        System.out.println("Removed " + id);
    }
}
