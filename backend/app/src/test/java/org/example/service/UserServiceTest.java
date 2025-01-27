package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    @Autowired
    UserService userservice;

    @Autowired
    UserRepository userrepository;

    // @Test
    // public void name(){
    //     //Given
    //     //When
    //     //Then
    // }

    @BeforeEach
    void setUp() {
        userrepository = Mockito.mock(UserRepository.class);
        userservice = new UserService(userrepository);
    }



    @Test
    public void notEmptyAllUser(){
        //Given
        User wronguser1 = new User();
        User wronguser2 = new User();
        User rightuser1 = new User();
        User rightuser2 = new User();

        wronguser1.setName("Gérard");
        wronguser2.setName("Paul");
        rightuser2.setName("Léo");
        rightuser1.setName("Léonard");

        Mockito.doReturn(List.of(rightuser1, rightuser2)).when(userrepository).findByNameLikeIgnoringCase("%Léo%");

        //When
        List<User> result = userservice.findAll("Léo");
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldFindAllUsers(){
        //Given
        User wronguser1 = new User();
        User wronguser2 = new User();
        User rightuser1 = new User();
        User rightuser2 = new User();

        wronguser1.setName("Gérard");
        wronguser2.setName("Paul");
        rightuser2.setName("Léo");
        rightuser1.setName("Léonard");

        Mockito.doReturn(List.of(rightuser1, rightuser2)).when(userrepository).findByNameLikeIgnoringCase("%Léo%");

        //When
        List<User> result = userservice.findAll("Léo");
        //Then
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    public void shouldFindAllUsersInOrdre(){
        //Given
        User wronguser1 = new User();
        User wronguser2 = new User();
        User rightuser1 = new User();
        User rightuser2 = new User();

        wronguser1.setName("Gérard");
        wronguser2.setName("Paul");
        rightuser2.setName("Léo");
        rightuser1.setName("Léonard");

        Mockito.doReturn(List.of(rightuser1, rightuser2)).when(userrepository).findByNameLikeIgnoringCase("%Léo%");

        //When
        List<User> result = userservice.findAll("Léo");
        //Then
        Assertions.assertThat(result).containsExactlyInAnyOrder(rightuser1, rightuser2);
    }

    @Test
    public void shouldFindOneUser() throws UserNotFoundException {
        //Given
        User wronguser1 = new User();
        User wronguser2 = new User();
        User rightuser1 = new User();

        wronguser1.setId(123);
        wronguser2.setId(80);
        rightuser1.setId(42);

        Mockito.doReturn(Optional.of(rightuser1)).when(userrepository).findById(42);

        //When
        User result = userservice.findOne(42);
        //Then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void shouldfindOneCorrectUser()throws UserNotFoundException{
        //Given
        User wronguser1 = new User();
        User wronguser2 = new User();
        User rightuser1 = new User();

        wronguser1.setId(123);
        wronguser2.setId(80);
        rightuser1.setId(42);

        Mockito.doReturn(Optional.of(rightuser1)).when(userrepository).findById(42);

        //When
        User result = userservice.findOne(42);
        //Then
        Assertions.assertThat(result.getId()).isEqualTo(42);
    }
}
