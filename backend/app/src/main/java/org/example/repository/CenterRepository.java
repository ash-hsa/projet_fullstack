package org.example.repository;

import java.util.List;

import org.example.service.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {


    public List<Center> findByName(String name);
    public List<Center> findById(int id);
}
