package org.example.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;


    private boolean is_s_admin;


    private boolean is_doctor;

    @Column(nullable = false)
    private String password;


    @OneToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name="address_docteur_fk"))
    private Address workAt;


    
    public User(){}

    public User(Integer id, String name, boolean is_s_admin, boolean is_doctor) {
        this.id = id;
        this.name = name;
        this.is_doctor = is_doctor;
        this.is_s_admin = is_s_admin;
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

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }
    

}