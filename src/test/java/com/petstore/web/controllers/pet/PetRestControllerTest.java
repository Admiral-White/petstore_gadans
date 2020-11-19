package com.petstore.web.controllers.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PetRestControllerTest {

    @Autowired MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }


    @Test
    void whenICallTheCreatePetPostMethod_thenCreateAPetObject() throws Exception{
        Pet pet = new Pet();
        pet.setName("silk");
        pet.setColor("color");
        pet.setPetSex(Gender.MALE);
        pet.setBreed("cat");
        pet.setAge(7);


        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/pet/create")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(pet)))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();



    }
}