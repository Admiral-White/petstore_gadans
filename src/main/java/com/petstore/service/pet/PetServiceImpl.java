package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired // to make this component readily available during runtime we have to do the @injectMocks in the petServiceImplTest class.
    PetRepository petRepository;

    @Override
    public Pet savePet(Pet pet) {

        if (pet==null){
            throw new NullPointerException("pet object can not be null");
        }

        return petRepository.save(pet);  // initially this was not was set to null but that had to be set to return petRepository because of the @Verify in the test class
    }


    @Override
    public Pet updatePet(Pet pet) {
        return null;
    }

    @Override
    public Pet findPetById(Integer id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pet> findAllPet() {
        return null;
    }

    @Override
    public void deletePetById(Integer id) {

        petRepository.deleteById(id);

    }
}
