package org.example.repository;

import java.util.List;

import org.example.service.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameLikeIgnoringCase(String name);
    
    
        List<User> findByIsDoctorTrueAndWorkAt_Id(Integer centerId);
    
    

}
