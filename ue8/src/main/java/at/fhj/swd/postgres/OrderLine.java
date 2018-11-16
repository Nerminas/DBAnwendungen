package at.fhj.swd.postgres;

import javax.persistence.*;

@Entity
@Table( name = "order_line", schema = "ue08")
class OrderLine {

    @EmbeddedId
    private OrderLinePK pk;

   @ManyToOne(optional = false)
   //@JoinColumn(insertable = false, updatable = false)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(optional = false)
    //@JoinColumn(insertable = false, updatable = false)
    @MapsId("productId")
    private Product product;

    private Integer amount;

    protected OrderLine() {
    }

    public OrderLine(Order order, Product product, Integer amount) {
            this.pk = new OrderLinePK(order.getId(),product.getId());
            this.order = order;
            this.product = product;
            this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }


    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "pk=" + pk +
                ", order=" + order +
                ", product=" + product +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLine orderLine = (OrderLine) o;

        if (pk != null ? !pk.equals(orderLine.pk) : orderLine.pk != null) return false;
        if (order != null ? !order.equals(orderLine.order) : orderLine.order != null) return false;
        if (product != null ? !product.equals(orderLine.product) : orderLine.product != null) return false;
        return amount != null ? amount.equals(orderLine.amount) : orderLine.amount == null;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
