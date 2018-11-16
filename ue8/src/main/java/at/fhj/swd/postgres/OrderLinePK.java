package at.fhj.swd.postgres;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderLinePK implements Serializable {
    private Integer orderId;
    private Integer productId;

    protected OrderLinePK() {
    }

    public OrderLinePK(Integer orderId, Integer productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLinePK that = (OrderLinePK) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return productId != null ? productId.equals(that.productId) : that.productId == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderLinePK{" +
                "order_id=" + orderId +
                ", product_id=" + productId +
                '}';
    }
}
