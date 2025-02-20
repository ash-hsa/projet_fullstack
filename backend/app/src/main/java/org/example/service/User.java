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
    private String name;

    @Column(name = "is_doctor")
    private boolean isDoctor;

    @Column(name = "is_s_admin")
    private boolean isSAdmin;

    @OneToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name="address_docteur_fk"))
    private Address workAt;

    public User(){}

    

    public User(Integer id, String name, boolean isDoctor, boolean isSAdmin, Address workAt) {
        this.id = id;
        this.name = name;
        this.isDoctor = isDoctor;
        this.isSAdmin = isSAdmin;
        this.workAt = workAt;
    }
    

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isDoctor() { return isDoctor; }
    public void setDoctor(boolean doctor) { isDoctor = doctor; }

    public boolean isSAdmin() { return isSAdmin; }
    public void setSAdmin(boolean sAdmin) { isSAdmin = sAdmin; }
}

    


