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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;



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

    

    @DeleteMapping("/admin/user/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.removeOne(id);
    }

    @GetMapping("/public/center/{id}/doctors")
    public List<User> getDoctorsByCenter(@PathVariable("id") Integer centerId) {
        return service.findDoctorsByCenter(centerId);
    }

    @DeleteMapping("/admin/user/{id}/doctor")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Integer id) {
        service.removeDoctor(id);
        return ResponseEntity.ok("Médecin supprimé avec succès");
    }

    @PostMapping("/admin/users")
    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException {
        System.out.println("📥 JSON REÇU DU FRONT:");
        System.out.println("➡️ Name: " + user.getName());
        System.out.println("➡️ Password: " + user.getPassword());
        System.out.println("➡️ isDoctor (AVANT): " + user.isDoctor());
        System.out.println("➡️ isAdmin " + user.isAdmin());
        System.out.println("➡️ isSAdmin " + user.isSAdmin());

        // 🔹 Forcer la valeur de `isDoctor`
        // user.setDoctor(true);
        // user.setSAdmin(false);
        // user.setAdmin(false);

        System.out.println("🛠 MODIFICATION AVANT INSERTION:");
        System.out.println("➡️ isDoctor (APRÈS SET): " + user.isDoctor());

        // 🔹 Vérification avant de passer à `service.create`
        if (user.isDoctor()) {
            System.out.println("✅ isDoctor est bien TRUE avant insertion !");
        } else {
            System.out.println("❌ isDoctor est encore FALSE avant insertion !");
        }

        service.create(user);
        return ResponseEntity.created(new URI("user/" + user.getId())).build();
    }


    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Récupération de l'utilisateur depuis le service
        User user = service.findByName(username);

        return ResponseEntity.ok(user);
    }




    

    @ExceptionHandler
    public ResponseEntity<String> handle(UserNotFoundException ex) {
        return ResponseEntity.badRequest().body("L'utilisateur n'existe pas");
    }
}
