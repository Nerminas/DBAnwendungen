// Schadler, Reindl, Klug

package at.fhj.swd.postgres;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory;
        EntityManager manager;
        EntityTransaction transaction;

        final String persistenceUnitName = "products";

        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        manager = factory.createEntityManager();

        transaction = manager.getTransaction();

        transaction.begin();
        CityEntity testCity = new CityEntity();
        testCity.setId(1);
        testCity.setName("Graz");
        manager.persist(testCity);
        transaction.commit();

        transaction.begin();
        SightEntity testSight = new SightEntity();
        testSight.setId(1);
        testSight.setName("Uhrturm");
        testSight.setSightToCity(testCity);
        manager.persist(testSight);
        transaction.commit();


// TEARDOWN
        manager.close();
        factory.close();
    }
}