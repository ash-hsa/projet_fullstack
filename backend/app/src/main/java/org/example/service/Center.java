package org.example.service;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ForeignKey;

@Entity
public class Center {
    
    @Id
    private int id;
    private String name;

    @ManyToMany
    private List<User> admins;

    @ManyToMany
    private List<User> doctors;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "address_fk"))
    private Address address;

    public Center( ){}

    
    public Center(String name, Address address, List<User> admins, List<User> doctors) {
        this.name = name;
        this.address = address;
        this.admins = admins;
        this.doctors = doctors;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress(){
        return this.address;
    }
}
