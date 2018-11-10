package at.fhj.swd.postgres;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(schema = "ue06", name = "product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int amount;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<Order>();

    protected Product(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public void setOrders(List<Order> orders)
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

