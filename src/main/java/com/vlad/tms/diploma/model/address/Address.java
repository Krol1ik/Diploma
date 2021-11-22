package com.vlad.tms.diploma.model.address;

import com.vlad.tms.diploma.model.entity.Customer;
import com.vlad.tms.diploma.model.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Улица не может быть пустой")
    @Column (name = "Street")
    private String street;
    @NotEmpty(message = "Номер дома не может быть пустым")
    @Column (name = "Number_house")
    private String numberHouse;
    @Column (name = "Number_apartment")
    private String numberApartment;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "Country_id")
    private Country country;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "City_id")
    private City city;


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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", numberHouse='" + numberHouse + '\'' +
                ", numberApartment='" + numberApartment + '\'' +
                ", country=" + country +
                ", city=" + city +
                ", user=" + user +
                ", customer=" + customer +
                '}';
    }
}

