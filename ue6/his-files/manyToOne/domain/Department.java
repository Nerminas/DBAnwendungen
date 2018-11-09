/*
 * project    company
 * subproject manyToOne
 */

package company.manyToOne.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.persistence.*;


@Entity
@Table(schema = "manyToOne")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department")
    private Collection<Employee> employees = new ArrayList<>();

    protected Department() {
    }

    Department(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Department && ((Department) o).id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String deptName) {
        this.name = deptName;
    }

    void add(Employee employee) {
        if (employees.contains(employee))
            return;

        employees.add(employee);
    }

    void remove(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return (List<Employee>) employees;
    }

    @Override
    public String toString() {
        String string = "Department id: " + getId() + ", name: " + getName();

        if (employees == null)
            string += ", employees == null !!!";
        else if (employees.size() == 0)
            string += " employees: none";
        else {
            string += " employees: ";
            for (Employee emp : employees) string += " " + emp.getName();
        }

        return string;

    }
}
