package at.fhj.swd.postgres;

// Schadler, Reindl, Klug

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class OneToOneTest {

    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private static EntityTransaction transaction;

    private static final String persistenceUnitName = "relationtests";

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
    public void oneToOne() {
        transaction.begin();
        BoyEntity boy = new BoyEntity();
        boy.setAge(22);
        boy.setName("Franz");
        GirlEntity girl = new GirlEntity();
        girl.setAge(21);
        girl.setName("Sissy");
        girl.setRelation(boy);
        assertNotNull(girl);
        manager.persist(boy);
        manager.persist(girl);
        transaction.commit();
        System.out.printf("Sucefully created boy and girl, now removing");

        transaction.begin();
        manager.remove(girl);
        manager.remove(boy);
        transaction.commit();
        System.out.printf("Entities removed");
    }
}
