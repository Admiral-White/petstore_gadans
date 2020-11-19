package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetServiceImplTest {

    @Mock  // this annotation is used to create a mock instance for mockito tests
    PetRepository petRepository;


    @InjectMocks  // this annotation is used to inject PetService during runtime which wont have been available because of the @mock annotation on it.
    PetService petService = new PetServiceImpl();

    Pet testPet;  // global declaration

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testPet = new Pet();
    }

    @Test
    public void mockTheSavedPetToRepositoryTest(){

        when(petRepository.save(testPet)).thenReturn(testPet);  // 'when' is used when we are testing for a method with a return type other than void.
        petService.savePet(testPet);

        verify(petRepository, times(1)).save(testPet);  // used to verify the test written above.
    }

    @Test
    public void mockByFindByIdTest(){
        when(petRepository.findById(2)).thenReturn(Optional.of(testPet));
        petService.findPetById(2);

        verify(petRepository, times(1)).findById(2);
    }

    @Test
    void mockDeletePetRepositoryTest(){
        doNothing().when(petRepository).deleteById(2);  // this is used when the method's return type is null
        petService.deletePetById(2);

        verify(petRepository, times(1)).deleteById(2);
    }


}