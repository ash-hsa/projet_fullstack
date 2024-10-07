package org.example.service;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "docteur")
public class Doctor {

    @Id
    private Integer id;
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name="address_docteur_fk"))
    private Address workAt;


    @ManyToMany
    private List<Patient> patientele;

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

    

}
