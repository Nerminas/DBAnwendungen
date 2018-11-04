package at.fhj.swd.postgres;

import javax.persistence.*;

@Entity
@Table(schema = "ue05")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int amount;

    @ManyToOne(optional = true)
    private Storage storage;

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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {

        if (this.storage != null)
            throw new IllegalStateException("Product already stored!");
        this.storage = storage;

        storage.addProduct(this);
    }

    public void removeStorage(Storage storage) {
        if ( ! this.storage.equals(storage))
            throw new IllegalStateException("Storage " + storage + " is not assigned!");
        this.storage = null;

        storage.removeProduct(this);
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

