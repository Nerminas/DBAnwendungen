package at.fhj.swd.postgres;/*
 * project    company
 * subproject simplest
 */

// Schadler, Reindl, Klug

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class StorageEntityTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "products";

    static final int id = 158;
    static final String name = "Teststorage";

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
        StorageEntity testStorage = new StorageEntity();
        testStorage.setLocation(17);
        testStorage.setAmount(100);
        assertNotNull(testStorage);
        manager.persist(testStorage);
        transaction.commit();

        System.out.println("Created and Persisted " + testStorage);

    }

    @Test
    public void modify() {
        StorageEntity testStorage = manager.find(StorageEntity.class, 1);
        assertNotNull(testStorage);
        System.out.println("Found " + testStorage);

        transaction.begin();
        testStorage.setLocation(1286);
        transaction.commit();

        testStorage = manager.find(StorageEntity.class, 1);

        assertEquals(1286, testStorage.getLocation());
        System.out.println("Updated " + testStorage);
    }

    @Test
    public void remove() {
        StorageEntity testStorage = manager.find(StorageEntity.class, 1);
        assertNotNull(testStorage);

        transaction.begin();
        manager.remove(testStorage);
        transaction.commit();

        testStorage = manager.find(StorageEntity.class, 1);
        assertNull(testStorage);

        System.out.println("Removed " + id);
    }
}
