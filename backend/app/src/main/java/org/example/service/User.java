package org.example.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "is_doctor", nullable = false)
    private boolean isDoctor = true;  

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;
    
    @Column(name = "is_s_admin", nullable = false)
    private boolean isSAdmin;



    @Column(name = "address_id") // 🔹 Ajout de l'attribut pour le centre de vaccination
    private Integer addressId;

    private String tel;
    private String mail;

    public User() {}

    public User(Integer id, String name, String password, boolean isDoctor,  boolean isAdmin, boolean isSAdmin, Integer addressId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isDoctor = isDoctor;
        this.isAdmin = isAdmin;
        this.isSAdmin = isSAdmin;
        this.addressId = addressId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isDoctor() { return isDoctor; }
    public void setDoctor(boolean doctor) { isDoctor = doctor; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean Admin) { isAdmin = Admin; }

    public boolean isSAdmin() { return isSAdmin; }
    public void setSAdmin(boolean sAdmin) { isSAdmin = sAdmin; }

    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }
}
