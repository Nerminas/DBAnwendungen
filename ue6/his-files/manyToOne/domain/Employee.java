/*
 * project    company
 * subproject manyToOne
 */

package company.manyToOne.domain;

import javax.persistence.*;


@Entity
@Table(schema = "manyToOne")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @ManyToOne(optional = true)
    private Department department;

    protected Employee() {
    }

    Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Employee && ((Employee) o).id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public void set(Department department) {

        if (this.department != null)
            throw new IllegalStateException("Department already set!");

        this.department = department;

        department.add(this);
    }


    public void moveTo(Department department) {
        if (this.department != null) {
            if (department.equals(this.department))        // no change
                return;

            this.department.remove(this);

            this.department = null;
        }

        set(department);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + ' ' + lastName;
    }


    @Override
    public String toString() {

        String string = "Employee id: " + getId() + " name: " + getName();

        if (getDepartment() != null)
            string += " department: " + getDepartment();

        return string;
    }

}
