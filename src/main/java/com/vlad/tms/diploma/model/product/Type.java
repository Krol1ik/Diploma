package com.vlad.tms.diploma.model.product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Type_product")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "Type_name", nullable = false)
    private String typeName;

    @OneToMany (mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public Type() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
