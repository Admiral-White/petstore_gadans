package com.petstore.data.repository;

import com.petstore.data.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
// the JpaRepository comes with predefined method which helps us in the manipulation of data in the Store class.

    Store findByName(String name);  // custom method is used to make a declearation and an implementation class is not need

}
