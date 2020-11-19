package com.petstore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petstore.data.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Integer> {

}
