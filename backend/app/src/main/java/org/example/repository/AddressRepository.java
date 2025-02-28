package org.example.repository;

import java.util.List;

import org.example.service.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    public List<Address> findByStreetLikeIgnoringCase(String name);
}
