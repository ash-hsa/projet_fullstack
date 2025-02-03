package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.CenterNotFoundException;
import org.example.exception.PatientNotFoundException;
import org.example.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PatientServiceTest {
    @Autowired
    PatientService patientservice;

    @Autowired
    PatientRepository patientrepository;

    // @Test
    // public void name(){
    //     //Given
    //     //When
    //     //Then
    // }

    @BeforeEach
    void setUp() {
        patientrepository = Mockito.mock(PatientRepository.class);
        patientservice = new PatientService(patientrepository);
    }



    @Test
    public void notEmptyAllPatient(){
        //Given
        Patient wrongpatient1 = new Patient();
        Patient wrongpatient2 = new Patient();
        Patient rightpatient1 = new Patient();
        Patient rightpatient2 = new Patient();

        wrongpatient1.setName("Gérard");
        wrongpatient2.setName("Paul");
        rightpatient2.setName("Léo");
        rightpatient1.setName("Léonard");

        Mockito.doReturn(List.of(rightpatient1, rightpatient2)).when(patientrepository).findByNameLikeIgnoringCase("%Léo%");

        //When
        List<Patient> result = patientservice.findAll("Léo");
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllPatients(){
        //Given
        Patient wrongpatient1 = new Patient();
        Patient wrongpatient2 = new Patient();
        Patient rightpatient1 = new Patient();
        Patient rightpatient2 = new Patient();

        wrongpatient1.setName("Gérard");
        wrongpatient2.setName("Paul");
        rightpatient2.setName("Léo");
        rightpatient1.setName("Léonard");

        Mockito.doReturn(List.of(rightpatient1, rightpatient2)).when(patientrepository).findByNameLikeIgnoringCase("%Léo%");

        //When
        List<Patient> result = patientservice.findAll("Léo");
        //Then
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    public void shouldFindAllPatientsInOrdre(){
        //Given
        Patient wrongpatient1 = new Patient();
        Patient wrongpatient2 = new Patient();
        Patient rightpatient1 = new Patient();
        Patient rightpatient2 = new Patient();

        wrongpatient1.setName("Gérard");
        wrongpatient2.setName("Paul");
        rightpatient1.setName("Léo");
        rightpatient2.setName("Léonard");

        Mockito.doReturn(List.of(rightpatient1, rightpatient2)).when(patientrepository).findByNameLikeIgnoringCase("%Léo%");

        //When
        List<Patient> result = patientservice.findAll("Léo");
        //Then
        Assertions.assertThat(result).containsExactlyInAnyOrder(rightpatient1, rightpatient2);
    }

    @Test
    public void shouldFindOnePatient() throws PatientNotFoundException {
        //Given
        Patient wrongpatient1 = new Patient();
        Patient wrongpatient2 = new Patient();
        Patient rightpatient1 = new Patient();

        wrongpatient1.setId(123);
        wrongpatient2.setId(80);
        rightpatient1.setId(42);

        Mockito.doReturn(Optional.of(rightpatient1)).when(patientrepository).findById(42);

        //When
        Patient result = patientservice.findOne(42);
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldfindOneCorrectPatient()throws PatientNotFoundException{
        //Given
        Patient wrongpatient1 = new Patient();
        Patient wrongpatient2 = new Patient();
        Patient rightpatient1 = new Patient();

        wrongpatient1.setId(123);
        wrongpatient2.setId(80);
        rightpatient1.setId(42);

        Mockito.doReturn(Optional.of(rightpatient1)).when(patientrepository).findById(42);

        //When
        Patient result = patientservice.findOne(42);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(42);
    }
}
