package com.vlad.tms.diploma.model.entity;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.order.DataOrder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, message = "Имя должно быть более 2 символов")
    @Size(max = 20, message = "Имя не должно превышать более 20 символов")
    @Column(name = "First_name")
    private String firstName;
    @Size(min = 2, message = "Фамилия должна быть более 2 символов")
    @Size(max = 20, message = "Фамилия не должна превышать более 20 символов")
    @Column(name = "Last_name")
    private String lastName;
    @Size(min = 9, message = "Телефон должен содержать более 9 цифр")
    @Size(max = 15, message = "Телефон должен содержать не более 15 цифр")
    @Column(name = "Phone_number")
    private String phoneNumber;
    @Email(message = "Вы ввели e-mail некорректно")
    @NotEmpty(message = "E-mail не может быть пустым")
    @Column(name = "Email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DataOrder> order;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<DataOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DataOrder> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", order=" + order +
                '}';
    }
}