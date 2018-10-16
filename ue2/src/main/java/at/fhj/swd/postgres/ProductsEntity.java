package at.fhj.swd.postgres;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "ue02", catalog = "swd_ws18_13")
public class ProductsEntity {
    private int id;
    private String productName;
    @Enumerated(EnumType.STRING)
    private ProductBarcode barcodeType;

//    public ProductsEntity(int id, String productName, ProductBarcode barcodeType) {
    public ProductsEntity(String productName, ProductBarcode barcodeType) {
        this.setId(id);
        this.setProductName(productName);
        this.setBarcodeType(barcodeType);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return Objects.hash(id, productName, barcodeType);
    }


    @Basic
    @Column(name = "barcode_type", nullable = true, length = 2)
    public ProductBarcode getBarcodeType() {
        return  this.barcodeType;
    }

    public void setBarcodeType(ProductBarcode barcodeType) {
        this.barcodeType = barcodeType;
    }
}
