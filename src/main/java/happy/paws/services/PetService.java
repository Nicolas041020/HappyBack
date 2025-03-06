package happy.paws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.Pet;
import happy.paws.model.User;
import happy.paws.repositories.UserRepository;
import happy.paws.repositories.PetRepository;

@Service
public class PetService {

    private PetRepository petRepository;
    private UserRepository userRepository;

    

    public PetService(PetRepository petRepository, UserRepository userRepository) {
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }



    public Pet savePet(Pet pet, int id_owner){
        User user = userRepository.findById(id_owner).orElse(null);
        pet.setUser(user);
        user.getPets().add(pet);
        petRepository.save(pet);
        userRepository.save(user);
        return pet;

    }

    public void savePets(List<Pet> pet, int id_owner){
        User user = userRepository.findById(id_owner).orElse(null);
        for(Pet p: pet){
            p.setUser(user);
            user.getPets().add(p);
            petRepository.save(p);
        }
        userRepository.save(user);
    }

    public Pet updatePet(int id, Pet pet){
        Pet temp = petRepository.findById(id).orElse(null);
        if (temp == null) {
            return null; 
        }
    
        if (pet.getName() != null) temp.setName(pet.getName());
        if (pet.getRace() != null) temp.setRace(pet.getRace());
        if (pet.getAmount_of_walks() != 0) temp.setAmount_of_walks(pet.getAmount_of_walks());
        if (pet.getAmount_of_food() != 0) temp.setAmount_of_food(pet.getAmount_of_food());
        if (pet.getFood() != null) temp.setFood(pet.getFood());
        if (pet.getWeight() != 0) temp.setWeight(pet.getWeight());
        if (pet.getDescription() != null) temp.setDescription(pet.getDescription());
        if (pet.getAge() != 0) temp.setAge(pet.getAge());
        petRepository.save(temp);
        return temp;

    }

    public List<Pet> getAllPets(int id_owner){
        return petRepository.getAllPets(id_owner);
    }
}
