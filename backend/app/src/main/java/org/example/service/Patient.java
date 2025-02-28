package org.example.service;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Patient {

    @Id
    private Integer id;
    private String name;
    private Date birthDate;
    private boolean is_vaccinated;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "address_fk"))
    private Address address;



    public Patient(){}

    public Patient(Integer id, String name, Date birthDate,boolean is_vaccinated, Address address) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.is_vaccinated = is_vaccinated;
        this.address = address;
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
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    
}
