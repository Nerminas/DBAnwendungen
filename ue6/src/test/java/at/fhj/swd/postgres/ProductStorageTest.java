package at.fhj.swd.postgres;/*
 * project    company
 * subproject simplest
 */

// Schadler, Reindl, Klug

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.Collection;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ProductStorageTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "products";

    static final String testProductName1 = "Testproduct1";
    static final String testProductName2 = "Testproduct2";

    static final String testStorageType = "DEFAULT";

    static int testProduct1Id;
    static int testProduct2Id;
    static int testStorageId;


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

        // create products
        Product testProduct1 = new Product();
        testProduct1.setName(testProductName1);
        testProduct1.setAmount(5);

        Product testProduct2 = new Product();
        testProduct2.setName(testProductName2);
        testProduct2.setAmount(8);

        assertNotNull(testProduct1);
        assertNotNull(testProduct2);


        manager.persist(testProduct1);
        manager.persist(testProduct2);

        System.out.println("Created and Persisted <" + testProduct1 + "> and <" + testProduct2 + ">");


        // create storage
        Storage testStorage = new Storage();
        testStorage.setType(testStorageType);

        assertNotNull(testStorage);

        manager.persist(testStorage);

        transaction.commit();

        testProduct1Id = testProduct1.getId();
        testProduct2Id = testProduct2.getId();
        testStorageId = testStorage.getId();
    }

    @Test
    public void join() {

        transaction.begin();

        Product testProduct1 = manager.find (Product.class, testProduct1Id);
        assertNotNull(testProduct1);
        System.out.println("Found " + testProduct1);

        Product testProduct2 = manager.find (Product.class, testProduct2Id);
        assertNotNull(testProduct2);
        System.out.println("Found " + testProduct2);

        Storage testStorage = manager.find ( Storage.class, testStorageId);
        assertNotNull(testStorage);
        System.out.println("Found " + testStorage);

        testProduct1.setStorage(testStorage);
        testProduct2.setStorage(testStorage);

        transaction.commit();

        assertEquals(testStorage, testProduct1.getStorage());
        assertEquals(testStorage, testProduct2.getStorage());

        assertEquals(2, testStorage.getProducts().size() );

        Collection<Product> storageProducts = testStorage.getProducts();

        assertTrue(storageProducts.contains(testProduct1));
        assertTrue(storageProducts.contains(testProduct2));
    }

    @Test
    public void removeProduct() {

        transaction.begin();

        Product testProduct1 = manager.find (Product.class, testProduct1Id);
        assertNotNull(testProduct1);
        System.out.println("Found " + testProduct1);

        Storage testStorage = manager.find ( Storage.class, testStorageId);
        assertNotNull(testStorage);
        System.out.println("Found " + testStorage);

        testProduct1.removeStorage(testStorage);

        transaction.commit();

        assertNull(testProduct1.getStorage());

        assertEquals(1, testStorage.getProducts().size() );
        Collection<Product> storageProducts = testStorage.getProducts();

        assertFalse(storageProducts.contains(testProduct1));
    }
}
