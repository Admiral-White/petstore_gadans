package com.petstore.data.repository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts ={"classpath:db/insert.sql"})
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenISaveStoreToDb(){
        Store store = new Store();
        store.setContactNo("676767");
        store.setLocation("Uyo");
//        store.setId(45);
        store.setName("pink store");

        log.info("store object before saving --> {}", store);

        storeRepository.save(store);




        assertThat(store.getId()).isNotNull();




    }

    @Test
//    @Transactional
//    @Rollback(value = false)
    public void findAllStoresSavedToDatabase(){
        List <Store> store = storeRepository.findAll();
        log.info("stores retrieve from database --> {}", store);
        assertThat(store).isNotNull();
        assertThat(store.size()).isEqualTo(2);





    }

    @Test
    public void updateExistingStoreDetails(){
        Store store = storeRepository.findById(67).orElse(null);
        assertThat(store).isNotNull();
        assertThat(store.getLocation()).isEqualTo("cally");

        log.info("store object updated --> {}", store);

        store.setLocation("borno");
        storeRepository.save(store);
        log.info("store object before saving --> {}", store);
        assertThat(store.getLocation()).isEqualTo("borno");
    }

    @Test
    public void deleteStoreTest(){
        assertThat(storeRepository.existsById(67)).isTrue();
        storeRepository.deleteById(67);
        assertThat(storeRepository.existsById(67)).isFalse();
    }

    @Test
    public void findAllPetsInAStore(){
        Store store = storeRepository.findById(21).orElse(null);
        assertThat(store).isNotNull();
        assertThat(store.getPetList()).isNotNull();
        log.info("pets in the store of Id 21 --> {}", store.getPetList());
    }

    @Test
    public void findStoreByNameTest(){

        Store store = storeRepository.findByName("stars");

        log.info("store object --> {}", store);
        assertThat(store).isNotNull();
    }
}