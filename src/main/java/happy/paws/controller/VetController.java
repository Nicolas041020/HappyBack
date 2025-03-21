package happy.paws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Vet;
import happy.paws.services.VetService;

@RestController
@RequestMapping("/vet")
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @PostMapping("/save")
    private ResponseEntity<Vet> addVet(@RequestBody Vet vet){
        return ResponseEntity.ok(vetService.addVet(vet));
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody Vet vet){
        Vet vetr = vetService.logIn(vet.getEmail(),vet.getIdentification());
        if (vetr != null) return ResponseEntity.ok(vetr);
        return ResponseEntity.badRequest().body("Veterinario No registrado");
    }

}
