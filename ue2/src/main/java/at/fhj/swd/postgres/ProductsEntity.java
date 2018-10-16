package at.fhj.swd.postgres;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "ue02", catalog = "postgres")
public class ProductsEntity {
    private int id;
    private String productName;

    public ProductsEntity(int id, String productName) {
        this.setId(id);
        this.setProductName(productName);
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return id == that.id &&
                Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName);
    }
}
