package br.com.facol.uservault.domain;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String address_name;
    private String district;
    private String city;
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_create_id")
    private UserCreate userCreate;

    public Address() {}

    public Address(String cep, String address_name, String district, String city, String state, UserCreate userCreate) {
        this.cep = cep;
        this.address_name = address_name;
        this.district = district;
        this.city = city;
        this.state = state;
        this.userCreate = userCreate;
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getAddress_name() {
        return address_name;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public UserCreate getUserCreate() {
        return userCreate;
    }
}
