package at.fhj.swd.postgres;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(schema = "ue08", name = "product")
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Collection<OrderLine> orderLines = new ArrayList<OrderLine>();

    protected Product(){}

    public int getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Collection<Order> getOrders() {
        Collection<Order> orders = new ArrayList<Order>();

        for (OrderLine orderLine : orderLines) {
            Order order = orderLine.getOrder();
            orders.add(order);
        }
        return orders;
    }

    public void assignOrder( Order order, Integer amount ) {
        OrderLine orderLine = new OrderLine(order, this, amount );
        if (orderLines.contains(orderLine)) {
            return;
        }
        orderLines.add(orderLine);
       // order.addProduct(this, amount);
    }

    public void removeOrder(OrderLine orderLine) {
        if ( ! orderLines.contains(orderLine))
            throw new IllegalStateException("OrderLine " + orderLine + " is not assigned!");
        orderLines.remove(orderLine);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return orderLines != null ? orderLines.equals(product.orderLines) : product.orderLines == null;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
        return result;
    }
}

