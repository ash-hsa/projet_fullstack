package org.example.service;

import java.util.List;
import java.util.Optional;


import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    public UserService(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(String name){
        if(name == null){
            name = "";
        }
        return userRepository.findByNameLikeIgnoringCase("%" + name + "%");
    }

    public User findOne(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
    }

    public void create(User p){
        userRepository.save(p);
    }

    public void removeOne(Integer id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).get(0);
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getName())
            .password(user.getPassword()) // Assure-toi que c'est bien encodé !
            .roles("USER") // Mets ici les rôles si besoin
            .build();
    }

    public void removeDoctor(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().isDoctor()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }
    

    public List<User> findDoctorsByCenter(Integer centerId) {
        return userRepository.findByIsDoctorTrueAndAddressId(centerId);

    }
}
