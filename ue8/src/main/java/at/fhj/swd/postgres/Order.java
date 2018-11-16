package at.fhj.swd.postgres;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table( schema = "ue08", name = "order")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private String orderNumber;

    @OneToMany ( mappedBy = "order")
    private Collection<OrderLine> orderLines = new ArrayList<OrderLine>();

    protected Order(){}

    public int getId() {
        return orderId;
    }

    public void setOrderNumber(String ordernumber) {
        this.orderNumber = ordernumber;
    }


    public Collection<Product> getProducts() {
        Collection<Product> products = new ArrayList<Product>();

        for (OrderLine orderLine : orderLines) {
            Product product = orderLine.getProduct();
            products.add(product);
        }
        return products;
    }

    void addProduct( Product product, Integer amount ) {
        OrderLine orderLine = new OrderLine(this, product, amount );

        if (orderLines.contains(orderLine)) {
            return;
        }
        orderLines.add(orderLine);
        product.assignOrder(this, amount);
    }


    void removeProduct(Product product) {

        if ( ! getProducts().contains(product) )
            throw new IllegalStateException("Product " + product + " is not assigned!");

            Collection<OrderLine> orderLinesToRemove = new ArrayList<OrderLine>();
            for ( OrderLine orderLine : orderLines ) {
                if ( orderLine.getProduct().equals(product) ) {

                    orderLinesToRemove.add(orderLine);
                    product.removeOrder(orderLine);
                }
           }
           orderLines.removeAll(orderLinesToRemove);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        return orderLines != null ? orderLines.equals(order.orderLines) : order.orderLines == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderLines != null ? orderLines.hashCode() : 0);
        return result;
    }
}
