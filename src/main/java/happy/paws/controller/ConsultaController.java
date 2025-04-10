package happy.paws.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

import happy.paws.model.Consulta;
import happy.paws.model.Pet;
import happy.paws.model.Recordatory;
import happy.paws.services.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/add/{id}")
    private ResponseEntity<Consulta> addConsulta(@RequestBody Consulta consulta, @PathVariable("id") int pet_id){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(consulta.getFecha().toString()); 
            consulta.setFecha(new java.sql.Date(utilDate.getTime())); 
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    
        Consulta con = consultaService.addConsulta(consulta, pet_id);
        if (con!=null) return ResponseEntity.ok(con);
        return ResponseEntity.badRequest().build();
            
        
    }

    @GetMapping("/consulta/{id}")
    private ResponseEntity<List<Consulta>> getConsultasByPetId(@PathVariable("id") int pet_id){
       List<Consulta> lista = consultaService.getConsultas(pet_id);
       if (lista!=null) return ResponseEntity.ok(lista);
       return null;
    }

    @GetMapping("/consultas")
    private ResponseEntity<List<Consulta>> getConsultas(){
        List<Consulta> lista = consultaService.getCons();
        if (lista!=null) return ResponseEntity.ok(lista);
        return null;
    }

    @PatchMapping("/update/{con_id}")
    public ResponseEntity<Consulta> updateConsulta (@RequestBody Consulta consulta, @PathVariable("con_id") int con_id){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(consulta.getFecha().toString()); 
            consulta.setFecha(new java.sql.Date(utilDate.getTime())); 
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        LocalDate localDate = consulta.getFecha().toLocalDate().plusDays(1);
        consulta.setFecha(java.sql.Date.valueOf(localDate));
        Consulta cons = consultaService.updateConsulta(consulta, con_id);
        if (cons!= null) return ResponseEntity.ok(cons);

        return null;
        
    }

    @DeleteMapping("/delete/{con_id}")
    public ResponseEntity<Consulta> deleteCons(@PathVariable("con_id") int con_id){
        Consulta con = consultaService.deleteConsulta(con_id);
        if (con!= null) return ResponseEntity.ok(con);
        return null;
            
        

    }

    //estadisticas
    @GetMapping("/freq")
    public ResponseEntity<Pet> mostFreq(){
        return ResponseEntity.ok(consultaService.mascotaFrec());
    }
    //estadisticas
    @GetMapping("/state/{estado}")
    public ResponseEntity<List<Pet>> findByEstado(@PathVariable("estado") String estado){
        return ResponseEntity.ok(consultaService.state(estado));
    }

}
