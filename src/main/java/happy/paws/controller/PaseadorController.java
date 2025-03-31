package happy.paws.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Paseador;
import happy.paws.services.PaseadorService;

@RestController
@RequestMapping("/paseador")
public class PaseadorController {

    private PaseadorService paseadorService;

    public PaseadorController(PaseadorService paseadorService) {
        this.paseadorService = paseadorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Paseador> savePaseador(@RequestBody Paseador paseador){
        Paseador past = paseadorService.savePaseador(paseador);
        if (past!= null) {
            return ResponseEntity.ok(past);
        }return ResponseEntity.badRequest().build();
    }

    @GetMapping("/paseadores")
    public List<Paseador> getPaseadores(){
        return paseadorService.getPaseadores();
    }

    @PostMapping("/login")
    public ResponseEntity<Paseador> logIn(@RequestBody Paseador paseador){
        Paseador paseadort = paseadorService.logIn(paseador.getEmail(),paseador.getPassw());
        if (paseadort != null) return ResponseEntity.ok(paseadort);
        return null;
    }
}
