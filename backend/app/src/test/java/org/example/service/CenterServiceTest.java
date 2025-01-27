package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CenterServiceTest {

    @Autowired
    CenterService centerservice;

    @Autowired
    CenterRepository centerrepository;

    // @Test
    // public void itshouldgiveID(){
    //     //Given
    //     centre.setId(0);
    //     //When
    //     int id = centre.getId();
    //     //Then
    //     Assertions.assertThat(id).isEqualTo(0);
    // }

    // @Test
    // public void itshouldgiveName(){
    //     //Given
    //     centre.setName("Polytech");
    //     //When
    //     String name = centre.getName();
    //     //Then
    //     Assertions.assertThat(name).isEqualTo("Polytech");
    // }

    @BeforeEach
    void setUp() {
        centerrepository = Mockito.mock(CenterRepository.class);
        centerservice = new CenterService(centerrepository);
    }



    @Test
    public void NotEmptyAllCentre(){
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
    public void ShouldFindCentres(){
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
    public void ShouldFindCentresInOrdre(){
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

    



}
