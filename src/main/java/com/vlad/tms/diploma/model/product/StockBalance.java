package com.vlad.tms.diploma.model.product;

import javax.persistence.*;

@Entity
@Table(name = "Stock_balance")
public class StockBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "Product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    @Column (name = "Remain", nullable = false)
    private int remain;

    public StockBalance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}
