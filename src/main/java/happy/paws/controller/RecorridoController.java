package happy.paws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Recorrido;
import happy.paws.services.RecorridoService;

@RestController
@RequestMapping("/recorrido")
public class RecorridoController {

    private RecorridoService recorridoService;

    public RecorridoController(RecorridoService recorridoService) {
        this.recorridoService = recorridoService;
    }

    @PostMapping("/add")
    private ResponseEntity<Recorrido> add(@RequestBody Recorrido rec){
        Recorrido reco = recorridoService.add(rec);
        if (reco!=null) {
            return ResponseEntity.ok(reco);
        }
        return ResponseEntity.badRequest().build();
        
    }

    @GetMapping("/recorrido")
    private ResponseEntity<Recorrido> getLast(){
        Recorrido reco = recorridoService.getLast();
        if (reco!=null) {
            return ResponseEntity.ok(reco);
        }
        return ResponseEntity.badRequest().build();
        
    }
}
