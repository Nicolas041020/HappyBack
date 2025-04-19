package happy.paws.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import happy.paws.model.History;
import happy.paws.services.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/add/{pet_id}")
    public ResponseEntity<History> addHistory(@RequestBody History history, @PathVariable int pet_id){
        return ResponseEntity.ok(historyService.addHistory(history,pet_id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<History>> getAllHistories(){
        List<History> his = historyService.getAllHistories();
        if (his!=null) return ResponseEntity.ok(his);
        return null;
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<List<History>> getHistoryByPetId(@PathVariable("id") int id){
        List<History> his = historyService.getHistoryByPetId(id);
        if(his!=null) return ResponseEntity.ok(his);
        return null;

    }

    @GetMapping("/find/{his_id}")
    private ResponseEntity<History> getHistory(@PathVariable("his_id") int his_id){
        return ResponseEntity.ok(historyService.getHistoryById(his_id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<History> updateHistory(@RequestBody History history,@PathVariable("id") int his_id){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(history.getDate().toString()); 
            history.setDate(new java.sql.Date(utilDate.getTime())); 
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        History hist = historyService.updateHistory(history, his_id);
        if (hist!=null) return ResponseEntity.ok(hist);
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<History> deleteHistory(@PathVariable("id") int id){
        return ResponseEntity.ok(historyService.deleteHistory(id));

    }

       //estadisticas
    @GetMapping("/between/{fecha1}/{fecha2}")
    public ResponseEntity<List<History>> getVaccinesBetween(@PathVariable("fecha1")String fecha1,@PathVariable("fecha2")String fecha2){
        Date d1,d2;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(fecha1);
            java.util.Date utilDate2 = sdf.parse(fecha2); 
            d1 = new java.sql.Date(utilDate.getTime());
            d2 = new java.sql.Date(utilDate2.getTime());
             
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        List<History> list = historyService.findVaccinesB(d1, d2);
        if(list!=null) return ResponseEntity.ok(list);
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/numVac/{vaccine}")
    public ResponseEntity<Integer> getNumVaccines(@PathVariable("vaccine")String vaccine){
        int num = historyService.getNumVaccines(vaccine);
        if(num!= 0) return ResponseEntity.ok(num);
        return null;
    }
}
