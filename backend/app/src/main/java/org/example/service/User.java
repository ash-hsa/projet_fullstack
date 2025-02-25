package org.example.service;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("isDoctor")
    @Column(name = "is_doctor", nullable = true)
    private boolean isDoctor;  

    @JsonProperty("isAdmin")
    @Column(name = "is_admin", nullable = true)
    private boolean is_admin;

    @JsonProperty("isSAdmin")
    @Column(name = "is_s_admin", nullable = true)
    private boolean isSAdmin;

    @Column(name = "address_id") // 🔹 Ajout de l'attribut pour le centre de vaccination
    private Integer addressId;

    private String tel;
    private String mail;

    public User() {
        System.out.println("&&&&&&&&&&&&& VALEUR DE BASE : "+this.isAdmin());
    }

    public User(Integer id, String name, String password, boolean isDoctor, boolean isSAdmin, Integer addressId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isDoctor = isDoctor;
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

    public boolean isSAdmin() { return isSAdmin; }
    public void setSAdmin(boolean sAdmin) { isSAdmin = sAdmin; }

    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public boolean isAdmin() { return is_admin; }
    public void setAdmin(boolean isAdmin) { this.is_admin = isAdmin; }

}
