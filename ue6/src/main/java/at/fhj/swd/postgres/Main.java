// Schadler, Reindl, Klug

package at.fhj.swd.postgres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
/*
        EntityManagerFactory factory;
        EntityManager manager;
        EntityTransaction transaction;

        final String persistenceUnitName = "products";

        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        manager = factory.createEntityManager();

        transaction = manager.getTransaction();



        transaction.begin();
        Storage testStorage = new Storage();
        testStorage.setType("STANDARD");

        manager.persist(testStorage);
        transaction.commit();

        transaction.begin();
        Product testProduct1 = new Product();
        testProduct1.setName("Test Product 1");
        testProduct1.setAmount(10);
        testProduct1.setStorage(testStorage);

        manager.persist(testProduct1);
        transaction.commit();

        transaction.begin();
        Product testProduct2 = new Product();
        testProduct2.setName("Test Product 2");
        testProduct2.setAmount(15);
        testProduct2.setStorage(testStorage);

        manager.persist(testProduct2);
        transaction.commit();





// TEARDOWN
        manager.close();
        factory.close();
        */
    }
}