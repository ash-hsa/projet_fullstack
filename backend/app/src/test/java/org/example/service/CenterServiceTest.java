package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.CenterNotFoundException;
import org.example.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CenterServiceTest {

    @Autowired
    CenterService centerservice;

    @Autowired
    CenterRepository centerrepository;

    // @Test
    // public void name(){
    //     //Given
    //     //When
    //     //Then
    // }

    @BeforeEach
    void setUp() {
        centerrepository = Mockito.mock(CenterRepository.class);
        centerservice = new CenterService(centerrepository);
    }



    @Test
    public void notEmptyAllCentre(){
        //Given
        Center wrongcenter1 = new Center();
        Center wrongcenter2 = new Center();
        Center rightcenter1 = new Center();
        Center rightcenter2 = new Center();

        wrongcenter1.setName("Polytech");
        wrongcenter2.setName("Fac");
        rightcenter2.setName("Hopital de Nancy");
        rightcenter1.setName("Hopital de Bel air");

        Mockito.doReturn(List.of(rightcenter1, rightcenter2)).when(centerrepository).findByNameLikeIgnoringCase("%HoPiTale%");

        //When
        List<Center> result = centerservice.findAll("HoPiTale");
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllCentres(){
        //Given
        Center wrongcenter1 = new Center();
        Center wrongcenter2 = new Center();
        Center rightcenter1 = new Center();
        Center rightcenter2 = new Center();

        wrongcenter1.setName("Polytech");
        wrongcenter2.setName("Fac");
        rightcenter2.setName("Hopital de Nancy");
        rightcenter1.setName("Hopital de Bel air");

        Mockito.doReturn(List.of(rightcenter1, rightcenter2)).when(centerrepository).findByNameLikeIgnoringCase("%HoPiTale%");

        //When
        List<Center> result = centerservice.findAll("HoPiTale");
        //Then
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    public void shouldFindAllCentresInOrdre(){
        //Given
        Center wrongcenter1 = new Center();
        Center wrongcenter2 = new Center();
        Center rightcenter1 = new Center();
        Center rightcenter2 = new Center();

        wrongcenter1.setName("Polytech");
        wrongcenter2.setName("Fac");
        rightcenter2.setName("Hopital de Nancy");
        rightcenter1.setName("Hopital de Bel air");

        Mockito.doReturn(List.of(rightcenter1, rightcenter2)).when(centerrepository).findByNameLikeIgnoringCase("%HoPiTale%");

        //When
        List<Center> result = centerservice.findAll("HoPiTale");
        //Then
        Assertions.assertThat(result).containsExactlyInAnyOrder(rightcenter1, rightcenter2);
    }

     @Test
     public void shouldfindOneCenter() throws CenterNotFoundException {
         //Given
         Center wrongcenter1 = new Center();
         Center wrongcenter2 = new Center();
         Center rightcenter1 = new Center();

         wrongcenter1.setId(123);
         wrongcenter2.setId(80);
         rightcenter1.setId(42);

         Mockito.doReturn(Optional.of(rightcenter1)).when(centerrepository).findById(42);

         //When
         Center result = centerservice.findOne(42);
         //Then
         Assertions.assertThat(result).isNotNull();
     }

    @Test
    public void shouldfindOneCorrectCenter()throws CenterNotFoundException{
        //Given
        Center wrongcenter1 = new Center();
        Center wrongcenter2 = new Center();
        Center rightcenter1 = new Center();

        wrongcenter1.setId(123);
        wrongcenter2.setId(80);
        rightcenter1.setId(42);

        Mockito.doReturn(Optional.of(rightcenter1)).when(centerrepository).findById(42);

        //When
        Center result = centerservice.findOne(42);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(42);
    }
    



}
