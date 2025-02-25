package org.example;

import org.example.service.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void getGreeting(){
        System.out.println("L'Application se lance!");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create a new user
        User user = new User();
        user.setId(0);
        user.setName("toto");
        user.setPassword("tata");
        user.setDoctor(true);
        user.setSAdmin(true);
        user.setAdmin(true);
        user.setMail("admin@mail.com");
        user.setTel("0000000000");
        userService.create(user);
        System.out.println("Default user created");
    }
}