// Schadler, Reindl, Klug

package at.fhj.swd.postgres;

import at.fhj.swd.postgres.ProductsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class Main {

    public static void main(String[] args) {

// SETUP
        EntityManagerFactory factory;
        EntityManager manager;
        EntityTransaction transaction;

        final String persistenceUnitName = "products";

        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        manager = factory.createEntityManager();

        transaction = manager.getTransaction();

// DO SOMETHING
        for (int i = 0; i <= 10; i++) {

            transaction.begin();
            ProductsEntity testProduct = new ProductsEntity("Product " + i, ProductBarcode.GS1);
            manager.persist(testProduct);
            transaction.commit();

            System.out.println("Created and Persisted " + testProduct);
        }

// TEARDOWN
        manager.close();
        factory.close();
    }
}