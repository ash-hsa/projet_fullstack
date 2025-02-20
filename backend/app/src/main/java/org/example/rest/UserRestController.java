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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // 🔹 Ajout du préfixe "/api"
public class UserRestController {

    @Autowired
    private UserService service;


    @GetMapping("/admin/users")
    public List<User> findAll(@RequestParam(name = "name", required = false) String filterByName) {
        return service.findAll(filterByName);
    }

    @GetMapping("/admin/user/{id2}")
    public User findAll(@PathVariable("id2") Integer id) throws UserNotFoundException {
        return service.findOne(id);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<User> create(@RequestBody User p) throws URISyntaxException {
        service.create(p);
        return ResponseEntity.created(new URI("user/" + p.getId())).build();
    }

    @DeleteMapping("/admin/user/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.removeOne(id);
    }

    @GetMapping(path = "/api/public/center/{id}/doctors")
    public List<User> getDoctorsByCenter(@PathVariable("id") Integer centerId) {
        return service.findDoctorsByCenter(centerId);
    }


    @DeleteMapping("/admin/user/{id}/doctor")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Integer id) {
        service.removeDoctor(id);
        return ResponseEntity.ok("Médecin supprimé avec succès");
    }

    @PostMapping("/medecins")
    public ResponseEntity<?> ajouterMedecin(@RequestBody User user) {
        System.out.println("Médecin reçu : " + user);
    
        if (user.getName() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Le nom et le mot de passe sont obligatoires");
        }
    
        user.setDoctor(true);
        user.setSAdmin(false);
    
        // 🔹 Assigner le centre de vaccination de l'admin (1 en dur)
        user.setAddressId(1);
    
        service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    

    @ExceptionHandler
    public ResponseEntity<String> handle(UserNotFoundException ex) {
        return ResponseEntity.badRequest().body("L'utilisateur n'existe pas");
    }
}
