package at.fhj.swd.postgres;/*
 * project    company
 * subproject simplest
 */

// Schadler, Reindl, Klug

import at.fhj.swd.Marine;
import at.fhj.swd.Pirate;
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
public class InerhitenceTest
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "products";

    Pirate ruffy;
    Marine garp;


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

        // create ruffy
        ruffy = new Pirate("ruffy","strawhat");
        assertNotNull(ruffy);
        manager.persist(ruffy);
        System.out.println("Created and Persisted <" + ruffy + ">");
        transaction.commit();


        transaction.begin();
        //create garp
        garp = new Marine("garp", "marinefort");
        assertNotNull(garp);
        manager.persist(garp);
        System.out.println("Created and Persisted <" + garp + ">");

        transaction.commit();
    }

    @Test
    public void find(){
        Pirate ruffy = manager.find (Pirate.class, 1);
        assertNotNull(ruffy);
        Marine garp = manager.find (Marine.class, 2);
        assertNotNull(garp);
    }

    @Test
    public void removeProduct() {

        transaction.begin();

        Pirate ruffy = manager.find (Pirate.class, 1);
        assertNotNull(ruffy);
        System.out.println("Found " + ruffy);
        manager.remove(ruffy);


        Marine garp = manager.find (Marine.class, 2);
        assertNotNull(garp);
        System.out.println("Found " + garp);
        manager.remove(garp);

        transaction.commit();

        ruffy = manager.find (Pirate.class, 1);
        assertNull(ruffy);


    }
}
