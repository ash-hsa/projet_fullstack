package org.example.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.example.exception.CenterNotFoundException;
import org.example.service.Center;
import org.example.service.CenterService;
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
public class CenterRestController {

    @Autowired
    private CenterService service;

    @GetMapping(path = "/api/public/centers")
    public List<Center> findAll(
        @RequestParam(name = "name", required = false)String filterByName){
        return service.findAll(filterByName);
    }


    
    @GetMapping(path = "/api/public/center/{id2}")
    public Center findAll(@PathVariable("id2") Integer id) throws CenterNotFoundException{
        return service.findOne(id);
    }

    @PostMapping(path = "/api/admin/centers")
    public ResponseEntity<Center> create(@RequestBody Center c) throws URISyntaxException{
        service.create(c);
        return ResponseEntity.created(new URI("center/"+c.getId())).build();
    }

    @DeleteMapping(path = "/api/admin/center/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.removeOne(id);
    }


    @ExceptionHandler
    public ResponseEntity<String> handle(CenterNotFoundException ex){
        return ResponseEntity.badRequest().body("Le centre n'existe pas");
    }

}
