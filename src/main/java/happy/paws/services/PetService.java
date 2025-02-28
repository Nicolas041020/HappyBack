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



    public void savePet(Pet pet, int id_owner){
        User user = userRepository.findById(id_owner).orElse(null);
        pet.setUser(user);
        user.getPets().add(pet);
        petRepository.save(pet);
        userRepository.save(user);
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
}
