package org.example.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import org.example.exception.UserNotFoundException;
import org.example.service.User;
import org.example.service.UserService;
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
import org.springframework.web.util.UriBuilder;

@RestController
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping(path = "/admin/users")
    public List<User> findAll(@RequestParam(name = "name", required = false)String filterByName){
        return service.findAll(filterByName);
    }

    
    @GetMapping(path = "/admin/user/{id2}")
    public User findAll(@PathVariable("id2") Integer id) throws UserNotFoundException{
        return service.findOne(id);
    }

    @PostMapping(path = "/admin/users")
    public ResponseEntity<User> create(@RequestBody User p) throws URISyntaxException{
        service.create(p);
        return ResponseEntity.created(new URI("user/"+p.getId())).build();
    }

    @DeleteMapping(path = "/admin/user/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.removeOne(id);
    }


    @ExceptionHandler
    public ResponseEntity<String> handle(UserNotFoundException ex){
        return ResponseEntity.badRequest().body("L'utilisateur n'existe pas");
    }

}
