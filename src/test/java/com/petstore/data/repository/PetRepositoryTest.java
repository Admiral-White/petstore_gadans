package com.petstore.data.repository;

import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // this annotation helps specify that this class is a springBootTest
@Slf4j  // this annotation causes lombok to generate a logger field
@Sql(scripts ={"classpath:db/insert.sql"}) // this annotation helps us write sql scripts directly within the class (the path specified here is the insert.sql, gotten through the copy from root operation)
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;  // wired so that we can access CRUD method of our repository

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach  // this annotation is used to signal that the method should be executed before each @Test method in the current class
    void setUp() {
    }

    @Test  // this is used to specify a test method
    @Transactional // it keeps the transaction open till all the database elements from more than one database is filled up by default it takes only one database transaction
    @Rollback(value = false) // this is used when the transactional annotation is used to provide the values of the transaction operations, if not set then no values will be returned
    public void whenPetIsSaved_thenReturnPetId(){
        // steps 1: create an instance of the pet
        Pet pet = new Pet();
        pet.setName("jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetSex(Gender.MALE);

        log.info("pet instance before saving--> {}", pet);  // log.info is used to generate a log information

        // call repository saved method
        petRepository.save(pet);

        assertThat(pet.getId()).isNotNull();

        log.info("pet instance after saving --> {}", pet);

        // create a store
        Store store = new Store();
        store.setName("pet seller");
        store.setLocation("Yaba");
        store.setContactNo("9707768766");

        // map pet to store
        pet.setStore(store);

        // save pet
        petRepository.save(pet);

        //assertThat
        assertThat(pet.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();
        assertThat(store.getId()).isNotNull();




    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIAddPetsToAStore_thenICanFetchAList(){

        // create a store
        Store store1 = new Store();
        store1.setName("Sony pets");
        store1.setLocation("Washington");
        store1.setContactNo("+124-887-9754");

        Pet ranger = new Pet();
        ranger.setName("Kill switch");
        ranger.setAge(3);
        ranger.setBreed("cat");
        ranger.setColor("white");
        ranger.setPetSex(Gender.FEMALE);
        ranger.setStore(store1);

        Pet sally = new Pet();
        sally.setName("Kill switch");
        sally.setAge(3);
        sally.setBreed("cat");
        sally.setColor("white");
        sally.setPetSex(Gender.FEMALE);
        sally.setStore(store1);

        log.info("pet instance before saving--> {}", ranger);

        store1.addPets(ranger);


        // call repository saved method
        storeRepository.save(store1);

//        assertThat(ranger.getId()).isNotNull();

        log.info("pet instance after saving --> {}", store1);



        // map pet to store
        ranger.setStore(store1);
        sally.setStore(store1);

        // save pet
        petRepository.save(ranger);
        petRepository.save(sally);


        store1.addPets(ranger);
        store1.addPets(sally);

        //assertThat
        assertThat(ranger.getId()).isNotNull();
        assertThat(ranger.getStore()).isNotNull();
        assertThat(sally.getId()).isNotNull();
        assertThat(sally.getStore()).isNotNull();
        assertThat(store1.getId()).isNotNull();
        assertThat(store1.getPetList()).isNotNull();
    }

    @Test
    public void whenFindallPetIsCalled_thenReturnAllPetsInStore(){

        // find pets from the store
        List<Pet> savedPets = petRepository.findAll();
        log.info("fetched pets list from db--> {}", savedPets);

        // assert that pets exist
        assertThat(savedPets).isNotEmpty();
        assertThat(savedPets.size()).isEqualTo(3);



    }

    @Test
    public void updateExistingPetDetailsTest(){

        //  fetch a database
        Pet sally = petRepository.findById(31).orElse(null);
        log.info("pet object retrieve from database --> {}",sally);

        // assert the field
        assertThat(sally).isNotNull();
        assertThat(sally.getColor()).isEqualTo("blue");

        // update pet field
        sally.setColor("purple");

        // save pet
        petRepository.save(sally);
        log.info("after updating pet object --> {}",sally);


        // assert that updated field has changed
        assertThat(sally.getColor()).isEqualTo("purple");

    }

    @Test
    public void whenIdeletePetFromDatabase_thenPetIsDeleted(){
        // check if pet exists
        boolean result = petRepository.existsById(31);

        // assert that pet exists
        assertThat(result).isTrue();

        // delete pet
        petRepository.deleteById(31);

        // check if pet exists
        assertThat(petRepository.existsById(31)).isFalse();

        // assert that pet does not exist

    }


}