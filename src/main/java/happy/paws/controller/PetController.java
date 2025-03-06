package happy.paws.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
        petService.savePets(pet,id_owner);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet pet){
       petService.updatePet(id,pet);
        return ResponseEntity.ok().build();
        
    }

    @GetMapping("/getPets/{id_owner}")
    public ResponseEntity<List<Pet>> getAllPets(@PathVariable int id_owner){
        return ResponseEntity.ok(petService.getAllPets(id_owner));
    }
}
