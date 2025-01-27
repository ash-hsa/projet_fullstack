package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.RdvNotFoundException;
import org.example.repository.RdvRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RdvServiceTest {
    @Autowired
    RdvService rdvservice;

    @Autowired
    RdvRepository rdvrepository;

    // @Test
    // public void name(){
    //     //Given
    //     //When
    //     //Then
    // }

    @BeforeEach
    void setUp() {
        rdvrepository = Mockito.mock(RdvRepository.class);
        rdvservice = new RdvService(rdvrepository);
    }



    @Test
    public void notEmptyAllRdv(){
        //Given
        Rdv wrongrdv1 = new Rdv();
        Rdv wrongrdv2 = new Rdv();
        Rdv rightrdv1 = new Rdv();
        Rdv rightrdv2 = new Rdv();

        wrongrdv1.setDate(new Date(2025,02,14,8,35));
        wrongrdv2.setDate(new Date(2025,03,14,8,35));
        rightrdv2.setDate(new Date(2025,02,25,10,40));
        rightrdv1.setDate(new Date(2025,02,25));

        Mockito.doReturn(List.of(rightrdv1, rightrdv2)).when(rdvrepository).findByDate(new Date(2025,02,25));

        //When
        List<Rdv> result = rdvservice.findAll(new Date(2025,02,25));
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllRdvs(){
        //Given
        Rdv wrongrdv1 = new Rdv();
        Rdv wrongrdv2 = new Rdv();
        Rdv rightrdv1 = new Rdv();
        Rdv rightrdv2 = new Rdv();

        wrongrdv1.setDate(new Date(2025,02,14,8,35));
        wrongrdv2.setDate(new Date(2025,03,14,8,35));
        rightrdv2.setDate(new Date(2025,02,25,10,40));
        rightrdv1.setDate(new Date(2025,02,25));

        Mockito.doReturn(List.of(rightrdv1, rightrdv2)).when(rdvrepository).findByDate(new Date(2025,02,25));

        //When
        List<Rdv> result = rdvservice.findAll(new Date(2025,02,25));
        //Then
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    public void shouldFindAllRdvsInOrdre(){
        //Given
        Rdv wrongrdv1 = new Rdv();
        Rdv wrongrdv2 = new Rdv();
        Rdv rightrdv1 = new Rdv();
        Rdv rightrdv2 = new Rdv();

        wrongrdv1.setDate(new Date(2025,02,14,8,35));
        wrongrdv2.setDate(new Date(2025,03,14,8,35));
        rightrdv2.setDate(new Date(2025,02,25,10,40));
        rightrdv1.setDate(new Date(2025,02,25));

        Mockito.doReturn(List.of(rightrdv1, rightrdv2)).when(rdvrepository).findByDate(new Date(2025,02,25));

        //When
        List<Rdv> result = rdvservice.findAll(new Date(2025,02,25));
        //Then
        Assertions.assertThat(result).containsExactlyInAnyOrder(rightrdv1, rightrdv2);
    }

    @Test
    public void shouldFindOneRdv() throws RdvNotFoundException {
        //Given
        Rdv wrongrdv1 = new Rdv();
        Rdv wrongrdv2 = new Rdv();
        Rdv rightrdv1 = new Rdv();

        wrongrdv1.setId(123);
        wrongrdv2.setId(80);
        rightrdv1.setId(42);

        Mockito.doReturn(Optional.of(rightrdv1)).when(rdvrepository).findById(42);

        //When
        Rdv result = rdvservice.findOne(42);
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldfindOneCorrectRdv()throws RdvNotFoundException{
        //Given
        Rdv wrongrdv1 = new Rdv();
        Rdv wrongrdv2 = new Rdv();
        Rdv rightrdv1 = new Rdv();

        wrongrdv1.setId(123);
        wrongrdv2.setId(80);
        rightrdv1.setId(42);

        Mockito.doReturn(Optional.of(rightrdv1)).when(rdvrepository).findById(42);

        //When
        Rdv result = rdvservice.findOne(42);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(42);
    }
}
