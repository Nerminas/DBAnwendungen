package at.fhj.swd.postgres;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table( schema = "ue06", name = "order")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ordernumber;

    @ManyToMany
    @JoinTable(
        schema = "ue06",
        name = "order_product",
        joinColumns = { @JoinColumn(name = "order_id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private Collection<Product> products = new ArrayList<Product>();

    protected Order(){}

    public int getId() {
        return id;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }


    public Collection<Product> getProducts() {
        return products;
    }

    void addProduct(Product product) {
        if (products.contains(product)) {
            System.out.print("Do NOT add product");
            return;
        }
        products.add(product);
        System.out.print("ADD product");
    }

    void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
