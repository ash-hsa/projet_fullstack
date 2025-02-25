package org.example.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.example.exception.PatientNotFoundException;
import org.example.exception.RdvNotFoundException;
import org.example.service.Patient;
import org.example.service.Rdv;
import org.example.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RdvRestController {

    @Autowired
    private RdvService service;

    @GetMapping(path = "/api/public/timeslots")
    public List<Rdv> findAll(
            @RequestParam(name = "date", required = false)Date date){
        System.out.println(service.findAll(date));
        return service.findAll(date);
    }

    @GetMapping(path = "/api/public/timeslots/{id}")
    public Rdv findAll(@PathVariable("id") Integer id) throws RdvNotFoundException{
        return service.findOne(id);
    }

    @PostMapping(path = "/api/public/timeslots")
    public ResponseEntity<Patient> create(@RequestBody Rdv r) throws URISyntaxException{
        service.create(r);
        return ResponseEntity.created(new URI("/public/timeslots/"+r.getId())).build();
    }

    @DeleteMapping(path = "/api/public/timeslots/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.removeOne(id);
    }

    @GetMapping(path = "/api/public/timeslots/patient/{id}")
        public List<Rdv> getRdvByPatient(@PathVariable("id") Integer id) throws PatientNotFoundException {
            System.out.println("Recherche des RDV pour le patient ID : " + id);

    // Vérifier si le patient existe
            Patient patient = service.findPatientById(id);

    // Récupérer les RDV du patient
         List<Rdv> rdvs = service.findAllforPatient(patient);

    // Afficher les résultats pour debug
            for (Rdv rdv : rdvs) {
               System.out.println("RDV trouvé : " + rdv.getId() + " - Date : " + rdv.getDate());
               System.out.println("Patient : " + (rdv.getPatient() != null ? rdv.getPatient().getName() : "NULL"));
              System.out.println("Docteur : " + (rdv.getDocteur() != null ? rdv.getDocteur().getName() : "NULL"));
              System.out.println("Centre : " + (rdv.getCenter() != null ? rdv.getCenter().getName() : "NULL"));
         }

            return rdvs;
        }




    @ExceptionHandler
    public ResponseEntity<String> handle(PatientNotFoundException ex){
        return ResponseEntity.badRequest().body("Le rendez-vous n'existe pas");
    }

}
