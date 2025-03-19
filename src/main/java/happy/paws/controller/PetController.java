package happy.paws.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Pet;
import happy.paws.services.PetService;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("save/{id_owner}")
    private ResponseEntity<Pet> savePet(@RequestBody Pet pet, @PathVariable int id_owner){
        Pet newPet = petService.savePet(pet,id_owner);
        return ResponseEntity.ok(newPet);
    }

    @PostMapping("saves/{id_owner}")
    private ResponseEntity<Pet> savePets(@RequestBody List<Pet> pet, @PathVariable int id_owner){
        pet.forEach(p -> System.out.println("Pet received: " + p.toString()));
        petService.savePets(pet,id_owner);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet pet){
       
        return ResponseEntity.ok(petService.updatePet(id,pet));
        
    }

    @GetMapping("/getPets/{id_owner}")
    public ResponseEntity<List<Pet>> getAllPets(@PathVariable int id_owner){
        return ResponseEntity.ok(petService.getAllPets(id_owner));
    }

    @GetMapping("/getPet/{id_pet}")
    public ResponseEntity<Pet> getPet(@PathVariable int id_pet){
        return ResponseEntity.ok(petService.getPetById(id_pet));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable("id") int pet_id){
        Pet petR = petService.deletePet(pet_id);
        if (petR != null) {
            return ResponseEntity.ok(petR);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}


