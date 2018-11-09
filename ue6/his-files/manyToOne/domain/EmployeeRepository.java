/*
 * project    company
 * subproject manyToOne
*/

package company.manyToOne.domain;

import spize.persistence.Persistence;

public class EmployeeRepository extends    company.persistence.Repository <Employee>
                                implements company.persistence.IRepository<Employee>
{

    public EmployeeRepository ()
    {
        super (Employee.class, "company.manyToOne");
    }

    public Employee create (String firstName, String lastName)
    {
        Employee emp = new Employee (firstName, lastName);
        entityManager.persist(emp);
        return emp;
    }

    static void reset ()
    {
		Persistence.resetTable    (schema, table);
		Persistence.resetSequence (schema, sequence);
	}

    static final String schema   = "manyToOne";
    static final String table    = "employee";
    static final String sequence = "employee_id_seq";
}
