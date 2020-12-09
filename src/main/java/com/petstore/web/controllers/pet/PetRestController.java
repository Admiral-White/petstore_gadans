package com.petstore.web.controllers.pet;

import com.petstore.data.model.Pet;
import com.petstore.service.pet.PetService;
import com.petstore.web.exception.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pet")
public class PetRestController {

    @Autowired
    PetService petService;

    @PostMapping("/create")
    public ResponseEntity<?> savePet(@RequestBody Pet pet) {           // ResponseEntity is used to map all response


        // log request body
        log.info("Request object --> {}", pet);
        // save request
        try {
            petService.savePet(pet);
        } catch (NullPointerException exe) {

            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(pet, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<?>getAllPets(){

        log.info("Get end point call");

        List<Pet> petList = petService.findAllPet();

        log.info("retrieved pets from database --> {}", petList);

        return ResponseEntity.ok().body(petList);
    }

    @GetMapping("one/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Integer id){

        log.info("id of pet to be found--> {}", id);

        Pet pet;
        try {
            pet = petService.findPetById(id);
        }catch (PetDoesNotExistException pex){

            return ResponseEntity.badRequest().body(pex.getMessage());
        }
        return ResponseEntity.ok().body(pet);

//        pet = petService.findPetById(id);

//        return ResponseEntity.ok().body(pet);
    }

    @PatchMapping("/one")
    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {

        try{
            pet = petService.updatePet(pet);
        }catch(PetDoesNotExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok().body(pet);
    }


    @DeleteMapping("/one/{id}")
    public ResponseEntity<?> deletePets(@PathVariable("id") Integer id) {

        log.info("Id of pet to be found --> {}", id);

        try{
            petService.deletePetById(id);
            }catch (PetDoesNotExistException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.noContent().build();
    }


    }
