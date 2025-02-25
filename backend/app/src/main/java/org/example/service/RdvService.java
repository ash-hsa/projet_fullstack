package org.example.service;

import java.util.Date;
import java.util.List;

import org.example.exception.PatientNotFoundException;
import org.example.exception.RdvNotFoundException;
import org.example.repository.PatientRepository;
import org.example.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RdvService {
    
    @Autowired
    private RdvRepository rdvRepository;

    public RdvService(final RdvRepository rdvrepository){
        this.rdvRepository = rdvrepository;
    }

    public List<Rdv> findAll(Date date){
        if (date == null) {
            return rdvRepository.findAll();
        }
        return rdvRepository.findByDate(date);
    }

    public List<Rdv> findAllforPatient(Patient patient){
        return rdvRepository.findByPatient(patient);
    }
    

    public Rdv findOne(Integer id) throws RdvNotFoundException{
        return rdvRepository.findById(id)
            .orElseThrow(RdvNotFoundException::new);
    }

    public void create(Rdv p){
        rdvRepository.save(p);
    }

    public void removeOne(Integer id){
        rdvRepository.deleteById(id);
    }

    @Autowired
    private PatientService patientService; // Injecter le service Patient

    public Patient findPatientById(Integer id) throws PatientNotFoundException {
    return patientService.findOne(id);
}

}
