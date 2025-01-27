package org.example.service;

import java.util.List;

import org.example.exception.UserNotFoundException;
import org.example.repository.RdvRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserService(final UserRepository userrepository){
        this.userRepository = userrepository;
    }

    public List<User> findAll(String name){
        if(name==null){
            name="";
        }
        return userRepository.findByNameLikeIgnoringCase("%"+name+"%");
    }

    public User findOne(Integer id) throws UserNotFoundException{
        return userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
    }

    public void create(User p){
        userRepository.save(p);
    }

    public void removeOne(Integer id){
        userRepository.deleteById(id);
    }
}
