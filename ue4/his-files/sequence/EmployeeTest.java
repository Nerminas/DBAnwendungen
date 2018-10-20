/*
 * project    company
 * subproject sequence
 */

package company.sequence.domain;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import spize.persistence.Transaction;

import company.persistence.Persistence;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeTest {

    static final boolean verbose = true;


    static final String emp_name = "John Doe";
    static final int emp_salary = 45000;
    static final int emp_id = 1001;

    static EmployeeRepository repository;

    private static void reset() {
        Transaction.begin();
        EmployeeRepository.reset();
        Transaction.commit();
    }

    @BeforeClass
    public static void setup() {
        repository = new EmployeeRepository();
        reset();
    }

    @AfterClass
    public static void teardown() {
        Persistence.close();
    }

    @Test
    public void create() {

        Transaction.begin();
        Employee emp = repository.create(emp_name, emp_salary);
        Transaction.commit();
        assertNotNull(emp);
        assertEquals(emp_id, emp.getId());
        if (verbose) System.out.println("Created and Persisted " + emp);
    }

    @Test
    public void verify() {
        Employee emp = repository.find(emp_id);
        assertNotNull(emp);
        List<Employee> emps = repository.findAll();
        assertEquals(1, emps.size());
        assertEquals(emp, emps.get(0));
    }
}
