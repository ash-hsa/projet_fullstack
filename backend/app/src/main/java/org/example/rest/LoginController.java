package org.example.rest;

import java.util.List;
import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/login")
public class LoginController {
    
    @GetMapping()
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }
    
    
}