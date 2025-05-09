package happy.paws.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@GetMapping("/filtro/{petId}/{fecha}")
public ResponseEntity<Double> getByFecha(
            @PathVariable("fecha") String fechaStr, @PathVariable("petId") int petId) {
        try {
            // Parseo de String a LocalDate
            LocalDate fecha = LocalDate.parse(fechaStr);

            // Llamada al servicio que filtra por LocalDate
            Double resultados = ritmoService.filtroPorFecha(fecha,petId);
            return ResponseEntity.ok(resultados);

        } catch (DateTimeParseException ex) {
            // Si el formato no coincide con "yyyy-MM-dd"
            return ResponseEntity
                    .badRequest()
                    .build();  // o puedes devolver un error más detallado
        }
    }
}
