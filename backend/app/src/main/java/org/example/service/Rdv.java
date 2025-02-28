package org.example.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Rdv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER) // Charger directement les relations
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rdvs"})
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docteur_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rdvs"})
    private User docteur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "rdvs"})
    private Center center;

    public Rdv() {}

    public Rdv(int id, Date date, Patient patient, User docteur, Center center) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.docteur = docteur;
        this.center = center;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDocteur() {
        return docteur;
    }

    public void setDocteur(User docteur) {
        this.docteur = docteur;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
