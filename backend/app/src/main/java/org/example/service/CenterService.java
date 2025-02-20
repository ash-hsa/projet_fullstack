package org.example.service;

import java.util.List;

import org.example.exception.CenterNotFoundException;
import org.example.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {
    
    @Autowired
    private CenterRepository centerRepository;

    public CenterService(final CenterRepository centerrepository){
        this.centerRepository = centerrepository;
    }

    public List<Center> findAll(String name){
        if(name==null){
            name="";
        }
        return centerRepository.findByNameLikeIgnoringCase("%"+name+"%");
    }

    public Center findOne(Integer id) throws CenterNotFoundException{
        return centerRepository.findById(id)
            .orElseThrow(CenterNotFoundException::new);
    }

    public void create(Center p){
        centerRepository.save(p);
    }

    public void removeOne(Integer id){
        centerRepository.deleteById(id);
    }

   
    
}
