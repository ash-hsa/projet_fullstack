package org.example.repository;

import java.util.List;

import org.example.service.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    public List<Patient> findByName(String name);
    public List<Patient> findById(int id);

}
