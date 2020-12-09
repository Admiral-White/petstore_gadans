package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exception.PetDoesNotExistException;
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
    public Pet updatePet(Pet pet) throws PetDoesNotExistException {

        Pet savedPet = petRepository.findById(pet.getId()).orElse(null);
        if (savedPet == null){
            throw new PetDoesNotExistException("pet with id"+ pet.getId()+"does not exist");

        }
        else{
            savedPet.setAge(pet.getAge());
            if(pet.getBreed() != null){
                savedPet.setBreed(pet.getBreed());
            }
            if(pet.getColor() != null){
                savedPet.setColor(pet.getColor());
            }
            if(pet.getPetSex() != null){
                savedPet.setPetSex(pet.getPetSex());
            }
            if(pet.getName() != null){
                savedPet.setName(pet.getName());
            }
            return petRepository.save(savedPet);
        }
    }

    @Override
    public Pet findPetById(Integer id) throws PetDoesNotExistException {
        Pet savePet = petRepository.findById(id).orElse(null);
            // check that pet exists
        if (savePet != null){
            return savePet;
        }
        else {
            throw new PetDoesNotExistException("Pet with id :" + id + "Does not " + "Exist");
        }
//        if (petRepository.existsById((id))) {
//            return petRepository.findById(id).get();
//        } else throw new PetDoesNotExistException("pet with id:" + "does not exist");

    }

    @Override
    public List<Pet> findAllPet() { return petRepository.findAll();
//        return null;
    }

    @Override
    public void deletePetById(Integer id) throws PetDoesNotExistException{

        try {
            petRepository.deleteById(id);
            }catch(Exception ex){
            throw new PetDoesNotExistException("\"Pet with the Id:\"+id+\"Does not\" +\n" + "Exist\"");
        }

//        petRepository.deleteById(id);

    }
}
