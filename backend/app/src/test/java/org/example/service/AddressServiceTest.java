package org.example.service;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;



public class AddressServiceTest {

    Address address = new Address();


    @Test
    public void itshouldgivestreet(){
        //Given
        address.setStreet("Rue Jean Lamour");
        //When
        String street = address.getStreet();
        //Then
        Assertions.assertThat(street).isEqualTo("Rue Jean Lamour");
    }

    @Test
    public void itshouldgiveID(){
        //Given
        address.setId(0);
        //When
        int id = address.getId();
        //Then
        Assertions.assertThat(id).isEqualTo(0);
    }

    @Test
    public void itshouldgiveCity(){
        //Given
        address.setCity("Vandœuvre-lès-Nancy");
        //When
        String city = address.getCity();
        //Then
        Assertions.assertThat(city).isEqualTo("Vandœuvre-lès-Nancy");
    }
    
    
}
