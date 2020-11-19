package com.petstore.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data  // this is used to generate getters and setter for hibernate behind the scene using lombok

public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // this field comes in as soon a class as being marked as an Entity (@Entity)

    private String name;
    private String location;
    private String contactNo;

    @ManyToOne  // doing a unidirectional mapping here
    @JoinColumn // used to map other columns
    private Store store;

    @OneToMany(mappedBy = "store", cascade= CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Pet> petList;

    public void addPets(Pet pet){

        if(petList == null){
            petList = new ArrayList<>();
        }

        petList.add(pet);
    }


}
