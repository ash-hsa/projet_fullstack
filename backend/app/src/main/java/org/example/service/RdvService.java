package org.example.service;

import java.util.Date;
import java.util.List;

import org.example.exception.RdvNotFoundException;
import org.example.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RdvService {
    
    @Autowired
    private RdvRepository rdvRepository;
    

    public List<Rdv> findAll(Date date){
        return rdvRepository.findByDate(date);
    }

    public Rdv findOne(Integer id) throws RdvNotFoundException{
        return rdvRepository.findById(id)
            .orElseThrow(RdvNotFoundException::new);
    }

    public void create(Rdv p){
        rdvRepository.save(p);
    }

    public void removeOne(Integer id){
        rdvRepository.deleteById(id);
    }
}
