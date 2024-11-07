package org.example.service;

import java.util.List;

import org.example.exception.PatientNotFoundException;
import org.example.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    

    public List<Patient> findAll(String name){
        return patientRepository.findByName(name);
    }

    public Patient findOne(Integer id) throws PatientNotFoundException{
        return patientRepository.findById(id)
            .orElseThrow(PatientNotFoundException::new);
    }

    public void create(Patient p){
        patientRepository.save(p);
    }

    public void removeOne(Integer id){
        patientRepository.deleteById(id);
    }
}
