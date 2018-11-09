package at.fhj.swd.postgres;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(schema = "ue06")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int amount;

    @ManyToMany
    private Collection<Order> orders = new ArrayList<Order>();

    public int getId() {
        return id;
    }


    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Collection<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(Collection<Order> orders)
    {
        if (this.orders != null)
            throw new IllegalStateException("Product already stored!");

        this.orders = orders;
    }

    public void addOrder(Order order)
    {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        if ( ! orders.contains(order))
            throw new IllegalStateException("Order " + order + " is not assigned!");

        orders.remove(order);

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

