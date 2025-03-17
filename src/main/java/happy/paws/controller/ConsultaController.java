package happy.paws.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Consulta;
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

}
