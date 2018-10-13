package at.fhj.swd.postgres;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "public", catalog = "postgres")
public class ProductsEntity {
    private int id;
    private String name;

    public ProductsEntity(int id, String name) {
        this.setId(id);
        this.setName(name);
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
