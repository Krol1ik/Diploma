package com.vlad.tms.diploma.model.order;

import com.vlad.tms.diploma.model.entity.User;
import com.vlad.tms.diploma.model.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "Order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "User_id")
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "Order_id")
    private DataOrder dataOrders;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "Product_id")
    private Product productOrder;

    @Column (name = "Count")
    private int count;

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DataOrder getDataOrders() {
        return dataOrders;
    }

    public void setDataOrders(DataOrder dataOrders) {
        this.dataOrders = dataOrders;
    }

    public Product getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(Product productOrder) {
        this.productOrder = productOrder;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
