package com.vlad.tms.diploma.model.address;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "Country_name", nullable = false)
    private String countryName;

    @OneToMany (mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address;

    @OneToMany (mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> city;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return countryName;
    }
}
