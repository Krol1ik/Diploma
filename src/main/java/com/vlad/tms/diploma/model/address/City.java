package com.vlad.tms.diploma.model.address;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "City_name", nullable = false)
    private String cityName;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "Country_id")
    private Country country;

    @OneToMany (mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> address;

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", country=" + country +
                ", address=" + address +
                '}';
    }
}
