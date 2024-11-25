package org.example.service;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Rdv {

    @Id
    private int  id;

    @OneToOne
    private Patient patient;

    @OneToOne
    private User docteur;

    @OneToOne
    private Center center;
    
    private Date date;

    public Rdv(){

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

}
