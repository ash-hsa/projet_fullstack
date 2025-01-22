package org.example.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CenterServiceTest {
    
    Center centre = new Center();


    @Test
    public void itshouldgiveID(){
        //Given
        centre.setId(0);
        //When
        int id = centre.getId();
        //Then
        Assertions.assertThat(id).isEqualTo(0);
    }

    @Test
    public void itshouldgiveName(){
        //Given
        centre.setName("Polytech");
        //When
        String name = centre.getName();
        //Then
        Assertions.assertThat(name).isEqualTo("Polytech");
    }

    



}
