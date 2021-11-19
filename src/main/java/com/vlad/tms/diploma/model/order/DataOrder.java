package com.vlad.tms.diploma.model.order;

import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class DataOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "Order_number")
    private Long orderNumber;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "User_id")
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "Customer_id")
    private Customer customer;


    @OneToMany (mappedBy = "dataOrders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItem;

    public DataOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "DataOrder{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", user=" + user +
                ", customer=" + customer +
                ", orderItem=" + orderItem +
                '}';
    }
}
