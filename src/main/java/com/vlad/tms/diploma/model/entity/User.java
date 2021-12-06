package com.vlad.tms.diploma.model.entity;

import com.vlad.tms.diploma.model.address.Address;
import com.vlad.tms.diploma.model.order.DataOrder;
import com.vlad.tms.diploma.model.order.OrderItem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Логин должен быть более 2 символов")
    @Size(max = 20, message = "Логин не должен превышать более 20 символов")
    private String username;
    @Size(min = 4, message = "Пароль должен быть более 4 символов")
    private String password;
    @Email(message = "Вы ввели e-mail некорректно")
    @NotEmpty(message = "E-mail не может быть пустым")
    private String email;
    @Size(min = 2, message = "Имя должен быть более 2 символов")
    @Size(max = 20, message = "Имя не должен превышать более 20 символов")
    @Column (name = "First_name", nullable = false)
    private String firstName;
    @Size(min = 2, message = "Фамилия должна быть более 2 символов")
    @Size(max = 20, message = "Фамилия не должна превышать более 20 символов")
    @Column (name = "Last_name", nullable = false)
    private String lastName;
    @Size(min = 9, message = "Телефон должен содержать более 9 цифр")
    @Size(max = 15, message = "Телефон должен содержать не более 15 цифр")
    @Column (name = "Phone_number", nullable = false)
    private String phoneNumber;
    private boolean active;
    private String activationCode;

    @ElementCollection(targetClass = RoleUser.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleUser> roleUsers;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Address_id", referencedColumnName = "id")
    private Address address;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DataOrder> order;

    public User() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Set<RoleUser> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<RoleUser> roleUsers) {
        this.roleUsers = roleUsers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<DataOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DataOrder> order) {
        this.order = order;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleUsers();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", activationCode='" + activationCode + '\'' +
                ", roleUsers=" + roleUsers +
                ", address=" + address +
                ", orderItems=" + orderItems +
                ", order=" + order +
                '}';
    }
}

