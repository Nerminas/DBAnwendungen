/*
 * project    company
 * subproject manyToOne
 */

package company.manyToOne.domain;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import spize.persistence.Transaction;

import company.persistence.Persistence;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class EmployeeDepartmentTest {
    static final boolean verbose = true;


    static final String emp1_firstName = "John";
    static final String emp1_lastName = "Doe";
    static final String emp2_firstName = "Homer";
    static final String emp2_lastName = "Simpson";

    static final String dep1_name = "Finance";
    static final String dep2_name = "HR";

    static EmployeeRepository empRepository = new EmployeeRepository();
    static DepartmentRepository depRepository = new DepartmentRepository();

    static Employee john;
    static Employee homer;

    static Department finance;
    static Department hr;

    @BeforeClass
    public static void setup() {
        empRepository = new EmployeeRepository();
        depRepository = new DepartmentRepository();

        Transaction.begin();
        //reset order matters ! :
        EmployeeRepository.reset();        // must be 1st !
        DepartmentRepository.reset();

        Transaction.commit();
    }

    @AfterClass
    public static void teardown() {
        Persistence.close();
    }


    @Test
    public void create() {
        Transaction.begin();

        john = empRepository.create(emp1_firstName, emp1_lastName);
        homer = empRepository.create(emp2_firstName, emp2_lastName);

        assertNotNull(john);
        assertNotNull(homer);

        //  create departments  ---------------------------------------

        finance = depRepository.create(dep1_name);
        hr = depRepository.create(dep2_name);

        assertNotNull(finance);
        assertNotNull(hr);

        Transaction.commit();
    }

    //  let employees work in Finances   --------------------------
    @Test
    public void join() {
        Transaction.begin();

        john.set(finance);
        homer.set(finance);

        Transaction.commit();
    }

    @Test
    public void verify() {
        if (verbose) System.out.println("Persisted " + john);
        if (verbose) System.out.println("Persisted " + homer);
        if (verbose) System.out.println("Persisted " + finance);
        if (verbose) System.out.println("Persisted " + hr);

        // Employees   ------------------------------------------------

        List<Employee> emps = empRepository.findAll();
        assertEquals(2, emps.size());

        if (verbose) empRepository.printAll();

        assertTrue(emps.contains(john));
        assertTrue(emps.contains(homer));

        assertEquals(finance, homer.getDepartment());
        assertEquals(finance, john.getDepartment());

        // Departments   ----------------------------------------------
        List<Department> deps = depRepository.findAll();
        assertEquals(2, deps.size());

        if (verbose) depRepository.printAll();

        assertEquals(2, finance.getEmployees().size());
        assertEquals(0, hr.getEmployees().size());

        List<Employee> financeEmps =
                deps.get(deps.indexOf(finance)).getEmployees();

        assertTrue(financeEmps.contains(john));
        assertTrue(financeEmps.contains(homer));

        //  let homer move to HR   ------------------------------------

        Transaction.begin();
        homer.moveTo(hr);
        Transaction.commit();

        assertEquals(finance, john.getDepartment());
        assertEquals(hr, homer.getDepartment());

        assertEquals(1, finance.getEmployees().size());
        assertEquals(1, hr.getEmployees().size());

        assertTrue(finance.getEmployees().contains(john));
        assertTrue(hr.getEmployees().contains(homer));


        if (verbose) depRepository.printAll();
    }
}
