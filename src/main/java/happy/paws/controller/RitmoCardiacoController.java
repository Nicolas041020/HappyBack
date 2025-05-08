package happy.paws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.RitmoCardiaco;
import happy.paws.services.HeartRateSimulatorService;
import happy.paws.services.RitmoCardiacoService;

@RestController
@RequestMapping("/ritmo")
public class RitmoCardiacoController {

    private RitmoCardiacoService ritmoService;

    
    public RitmoCardiacoController(RitmoCardiacoService ritmoService) {
        this.ritmoService = ritmoService;
    }


    @GetMapping("/heart-rate/{dogId}")
public RitmoCardiaco getHeartRate(@PathVariable int dogId) {
    return ritmoService.generarYGuardar(dogId);
}
}
