package com.petstore.data.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity  // this annotation is used on a data class to create entries
@Data  // this annotation is used to generate getters and setters methods for the data class behind the scene by lombok
public class Pet {

    @Id  // used to indicate that the member field below is the primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //  used to set the auto increment of the specified column (field)
    private Integer id;

    @Column(nullable = false)  // @Column is used to assigned an attribute to the column
    private String name;

    private String color;

//    @Column(unique = true)  this is comment out because we should not have only one breeds of Dogs. in other for the test to pass
    private String breed;

    private int age;

    @Enumerated(EnumType.STRING)  // used to map enums either by ORDINARY OR STRING (this will return the enum as a string in the database)
    private Gender petSex;

    @ManyToOne(cascade = CascadeType.PERSIST)  // cascade is used to work on data entities that are related or dependent on other entity to function properly (.PERSIST helps perform operations on a single entity and it affect all it associated members).
    @ToString.Exclude  // this is used to skip the instances below from being printed to a ToString (by default the printing is enabled to print all at runtime)
    private Store store;


}
