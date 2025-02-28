package org.example.service;

import java.util.List;

import org.example.exception.AddressNotFoundException;
import org.example.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    public AddressService(final AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll(String street){
        if(street==null){
            street="";
        }
        return addressRepository.findByStreetLikeIgnoringCase("%"+street+"%");
    }

    public Address findOne(Integer id) throws AddressNotFoundException{
        return addressRepository.findById(id)
            .orElseThrow(AddressNotFoundException::new);
    }

    public void create(Address a){
        addressRepository.save(a);
    }

    public void removeOne(Integer id){
        addressRepository.deleteById(id);
    }
}
