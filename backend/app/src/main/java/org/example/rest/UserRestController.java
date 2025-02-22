package org.example.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import org.example.exception.UserNotFoundException;
import org.example.service.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping(path = "/api/admin/users")
    public List<User> findAll(@RequestParam(name = "name", required = false)String filterByName){
        return service.findAll(filterByName);
    }

    
    @GetMapping(path = "/api/admin/user/{id2}")
    public User findAll(@PathVariable("id2") Integer id) throws UserNotFoundException{
        return service.findOne(id);
    }

    @PostMapping(path = "/api/admin/users")
    public ResponseEntity<User> create(@RequestBody User p) throws URISyntaxException{
        service.create(p);
        return ResponseEntity.created(new URI("user/"+p.getId())).build();
    }

    @DeleteMapping(path = "/api/admin/user/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.removeOne(id);
    }

    @GetMapping(path = "/api/public/center/{id}/doctors")
public List<User> getDoctorsByCenter(@PathVariable("id") Integer centerId) {
    return service.findDoctorsByCenter(centerId);
}

@DeleteMapping(path = "/api/admin/user/{id}/doctor")
public ResponseEntity<String> deleteDoctor(@PathVariable("id") Integer id) {
    service.removeDoctor(id); // L'exception est gérée par Spring automatiquement
    return ResponseEntity.ok("Médecin supprimé avec succès");
}





    @ExceptionHandler
    public ResponseEntity<String> handle(UserNotFoundException ex){
        return ResponseEntity.badRequest().body("L'utilisateur n'existe pas");
    }

}
