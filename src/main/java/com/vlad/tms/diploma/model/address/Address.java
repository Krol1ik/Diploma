package com.vlad.tms.diploma.model.address;

import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "Street")
    private String street;
    @Column (name = "Number_house")
    private String numberHouse;
    @Column (name = "Number_apartment")
    private String numberApartment;
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Country country;


    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private Customer customer;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getNumberApartment() {
        return numberApartment;
    }

    public void setNumberApartment(String numberApartment) {
        this.numberApartment = numberApartment;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
}

