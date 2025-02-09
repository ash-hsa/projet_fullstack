
package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.net.PasswordAuthentication;

import org.example.repository.UserRepository;
import org.example.service.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    
    
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    
    public void itShouldAllowUser()
            throws Exception {
        //Given
        User utilisateur = new User();
        utilisateur.setName("toto");
        utilisateur.setPassword(passwordEncoder.encode("tata"));
        utilisateur.setId(0);
        repository.save(utilisateur);
        System.out.println(repository.findAll());
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/centers")
                        .contentType(MediaType.APPLICATION_JSON)
                        // toto:tata
                        .header("Authorization","Basic dG90bzp0YXRh"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        
        //then
    }
    
    
}

