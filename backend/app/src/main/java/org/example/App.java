package org.example;

import java.util.List;

import org.example.repository.CenterRepository;
import org.example.repository.UserRepository;
import org.example.repository.AddressRepository;
import org.example.service.Address;
import org.example.service.Center;
import org.example.service.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private AddressRepository AddressRepository;

    public static void getGreeting(){
        System.out.println("L'Application se lance!");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }




    private Address createAddress(Integer id,String street, String city) {
        // Create an address
        Address address = new Address(id, street, city);
        AddressRepository.save(address);
        return address;

    }

    private User createUser(Integer id, String name, String password, boolean isDoctor, boolean isSAdmin, boolean isAdmin, Integer addressId, String tel,String mail) {
        // Create a user
        User user = new User(id, name, password, isDoctor, isSAdmin, isAdmin, addressId,tel,mail);
        userRepository.save(user);
        return user;
    }

    private Center createCenter(String nom, Address address, List<User> admin, List<User> doctor) {
        // Create a center
        Center center = new Center(nom, address, admin, doctor);
        centerRepository.save(center);
        return center;
    }



    

    @Override
    public void run(String... args) throws Exception {
        // 🔹 Création d'une adresse
        Address address0 = createAddress(1, "rue de la paix", "Paris");
        Address address1 = createAddress(2, "Avenue des Champs-Élysées", "Paris");
        Address address2 = createAddress(3, "5 Boulevard Haussmann", "Paris");
        Address address3 = createAddress(4, "10 Place Bellecour", "Lyon");
        Address address4 = createAddress(5, "1 Rue de la République", "Marseille");
        Address address5 = createAddress(6, "20 Rue du Faubourg Saint-Honoré", "Paris");
        Address address6 = createAddress(7, "15 Rue Sainte-Catherine", "Bordeaux");
        Address address7 = createAddress(8, "3 Quai de la Tournelle", "Paris");
        Address address8 = createAddress(9, "45 Boulevard Saint-Michel", "Paris");
        Address address9 = createAddress(10, "12 Rue du Capitole", "Toulouse");



        // 🔹 Création d'admins et doctors
        User Sadmin = createUser(1, "Sadmin", "Sadmin", false, true, true, 1,"Sadmin@mail.com", "0000000000");
        User admin = createUser(2, "Admin", "admin", false, false, true, 1,"admin@mail.com", "0600000000");
        User user = createUser(3, "User", "user", false, false, false, 1,"user@mail.com","0100000000");
        User Doctor = createUser(4, "Doctor", "doctor", true, false, false, 1,"doctor@mail.com","0200000000");

        // 🔹 Création du Centre avec admins et docteurs
        Center center = createCenter("Centre Paix", address0, List.of(admin), List.of(Doctor));
        // createCenter("Centre Elysées", address1, List.of(admin), List.of(Doctor));
        // createCenter("Centre Haussmann", address2, List.of(admin), List.of(Doctor));

        

        System.out.println("✅ Centre créé : " + center.getName());




    }
}