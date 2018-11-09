/*
 * project    company
 * subproject manyToOne
 */

package company.manyToOne.domain;

import spize.persistence.Persistence;

public class DepartmentRepository extends company.persistence.Repository<Department>
        implements company.persistence.IRepository<Department> {

    public DepartmentRepository() {
        super(Department.class, "company.manyToOne");
    }

    public Department create(String name) {
        Department dept = new Department(name);

        entityManager.persist(dept);

        return dept;
    }


    static void reset() {
        Persistence.resetTable(schema, table);
        Persistence.resetSequence(schema, sequence);
    }

    static final String schema = EmployeeRepository.schema;
    static final String table = "department";
    static final String sequence = "department_id_seq";

}
