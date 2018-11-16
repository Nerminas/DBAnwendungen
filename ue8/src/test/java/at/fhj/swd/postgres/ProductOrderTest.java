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
public class ProductOrderTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "products";

    static final String testProductName1 = "Testproduct1";
    static final String testProductName2 = "Testproduct2";

    static final String testOrdernumber1 = "CustomerOrder1";
    static final String testOrdernumber2 = "CustomerOrder2";

    static final Integer testAmount1 = 3;
    static final Integer testAmount2 = 6;

    static int testProduct1Id;
    static int testProduct2Id;
    static int testOrder1Id;
    static int testOrder2Id;


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

        Product testProduct2 = new Product();
        testProduct2.setName(testProductName2);

        assertNotNull(testProduct1);
        assertNotNull(testProduct2);


        manager.persist(testProduct1);
        manager.persist(testProduct2);

        System.out.println("Created and Persisted <" + testProduct1 + "> and <" + testProduct2 + ">");


        // create orders
        Order testOrder1 = new Order();
        testOrder1.setOrderNumber(testOrdernumber1);
        Order testOrder2 = new Order();
        testOrder2.setOrderNumber(testOrdernumber2);

        assertNotNull(testOrder1);
        assertNotNull(testOrder2);

        manager.persist(testOrder1);
        manager.persist(testOrder2);

        transaction.commit();

        testProduct1Id = testProduct1.getId();
        testProduct2Id = testProduct2.getId();
        testOrder1Id = testOrder1.getId();
        testOrder2Id =testOrder2.getId();
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

        Order testOrder1 = manager.find ( Order.class, testOrder1Id);
        assertNotNull(testOrder1);
        System.out.println("Found " + testOrder1);

        Order testOrder2 = manager.find ( Order.class, testOrder2Id);
        assertNotNull(testOrder1);
        System.out.println("Found " + testOrder2);


        testOrder1.addProduct(testProduct1, testAmount1);
        testOrder2.addProduct(testProduct1, testAmount2);

        testOrder1.addProduct(testProduct2, testAmount1);
        testOrder2.addProduct(testProduct2, testAmount2) ;

        transaction.commit();

        assertTrue(testProduct1.getOrders().contains(testOrder1));
        assertTrue(testProduct1.getOrders().contains(testOrder2));
        assertTrue(testOrder1.getProducts().contains(testProduct1));
        assertTrue(testOrder1.getProducts().contains(testProduct2));
        assertTrue(testProduct2.getOrders().contains(testOrder1));
        assertTrue(testProduct2.getOrders().contains(testOrder2));
        assertTrue(testOrder2.getProducts().contains(testProduct1));
        assertTrue(testOrder2.getProducts().contains(testProduct2));

        assertEquals(2, testOrder1.getProducts().size() );
        assertEquals(2, testOrder2.getProducts().size() );
        assertEquals(2, testProduct1.getOrders().size() );
        assertEquals(2, testProduct2.getOrders().size() );
    }

    @Test
    public void removeProduct() {

        transaction.begin();

        Order testOrder1 = manager.find ( Order.class, testOrder1Id);
        assertNotNull(testOrder1);
        System.out.println("Found " + testOrder1);

        Product testProduct1 = manager.find (Product.class, testProduct1Id);
        assertNotNull(testProduct1);
        System.out.println("Found " + testProduct1);

        Product testProduct2 = manager.find (Product.class, testProduct2Id);
        assertNotNull(testProduct2);
        System.out.println("Found " + testProduct2);


        testOrder1.removeProduct(testProduct1);
        testOrder1.removeProduct(testProduct2);

        transaction.commit();

        assertEquals(1,testProduct1.getOrders().size());
        assertEquals(0, testOrder1.getProducts().size() );

        Collection<Order> product1Orders = testProduct1.getOrders();
        Collection<Product> order1Products = testOrder1.getProducts();

        assertFalse(product1Orders.contains(testOrder1));
        assertFalse(order1Products.contains(testProduct1));
    }
}
