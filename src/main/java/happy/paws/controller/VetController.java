package happy.paws.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/vets")
    public ResponseEntity<List<Vet>> getVets(){
        List<Vet> vets = vetService.getAllVets();
        if (vets != null) return ResponseEntity.ok(vets);
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<Vet> logIn(@RequestBody Vet vet){
        Vet vetr = vetService.logIn(vet.getIdentification(),vet.getPassw());
        if (vetr != null) return ResponseEntity.ok(vetr);
        return null;
    }

}
