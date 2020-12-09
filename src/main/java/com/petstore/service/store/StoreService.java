package com.petstore.service.store;

import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import com.petstore.web.exception.StoreNotFoundException;

import java.util.List;

public interface StoreService {

    Store saveStore(Store store);
    Store updateStore(Store store);
    Store findStoreByName(Store store);
    Store findStoreById(Integer id);
    void deleteStoreById(Integer id);
    List<Store> findAllStore();
    Store addPet(Integer storeId, Pet pet) throws StoreNotFoundException;

}
