// Schadler, Reindl, Klug

package at.fhj.swd.postgres;

import at.fhj.swd.postgres.ProductsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class Main {
//    private static final SessionFactory ourSessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }

    //    public static void main(final String[] args) throws Exception {
    public static void main(String[] args) {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//            ourSessionFactory.close();
//        }


// SETUP
        EntityManagerFactory factory;
        EntityManager manager;
        EntityTransaction transaction;

        final String persistenceUnitName = "products";

        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);

        transaction = manager.getTransaction();

// DO SOMETHING
        for (int i = 0; i <= 10; i++) {

            transaction.begin();
            ProductsEntity testProduct = new ProductsEntity("Product " + i, ProductBarcode.EAN128);
//            testProduct.setId(i);
//            testProduct.setName("Product" + i);
            assertNotNull(testProduct);
            manager.persist(testProduct);
            transaction.commit();

            System.out.println("Created and Persisted " + testProduct);
        }

// TEARDOWN
        manager.close();
        factory.close();
    }
}