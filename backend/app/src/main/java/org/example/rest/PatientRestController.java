package org.example.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.example.exception.PatientNotFoundException;
import org.example.service.Patient;
import org.example.service.PatientService;
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
public class PatientRestController {

    @Autowired
    private PatientService service;

    @GetMapping(path = "/api/public/patients")
    public List<Patient> findAll(@RequestParam(name = "name", required = false)String filterByName){
        return service.findAll(filterByName);
    }

    
    @GetMapping(path = "/api/public/patient/{id2}")
    public Patient findAll(@PathVariable("id2") Integer id) throws PatientNotFoundException{
        return service.findOne(id);
    }

    @PostMapping(path = "/api/public/patients")
    public ResponseEntity<Patient> create(@RequestBody Patient p) throws URISyntaxException{
        service.create(p);
        return ResponseEntity.created(new URI("patient/"+p.getId())).build();
    }

    @DeleteMapping(path = "/api/public/patient/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.removeOne(id);
    }


    @ExceptionHandler
    public ResponseEntity<String> handle(PatientNotFoundException ex){
        return ResponseEntity.badRequest().body("Le patient n'existe pas");
    }

}
