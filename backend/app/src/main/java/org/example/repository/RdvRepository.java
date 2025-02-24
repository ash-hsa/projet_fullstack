package org.example.repository;

import java.util.Date;
import java.util.List;

import org.example.service.Patient;
import org.example.service.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Integer> {

    public List<Rdv> findByDate(Date date);
    public List<Rdv> findByPatient(Patient patient);
}
