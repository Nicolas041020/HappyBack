package happy.paws.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Recordatory;
import happy.paws.services.RecordatoryService;

@RestController
@RequestMapping("/recordatory")
public class RecordatoryController {

    private RecordatoryService recordatoryService;

    

    public RecordatoryController(RecordatoryService recordatoryService) {
        this.recordatoryService = recordatoryService;
    }



    @PostMapping("/add/{id}")
    public ResponseEntity<Recordatory> add(@RequestBody Recordatory recordatory, @PathVariable("id") int pet_id) throws Exception{
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(recordatory.getDate().toString()); 
            recordatory.setDate(new java.sql.Date(utilDate.getTime())); 
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        LocalDate localDate = recordatory.getDate().toLocalDate().plusDays(1);
        recordatory.setDate(java.sql.Date.valueOf(localDate));
        Recordatory rec = recordatoryService.add(recordatory, pet_id);
        if(rec!=null) return ResponseEntity.ok(rec);
        return null;
    }

    @GetMapping("/recs/{user_id}")
    public ResponseEntity<List<Recordatory>> verRecs(@PathVariable("user_id") int user_id){
       List<Recordatory> reco = recordatoryService.verRecs(user_id);
       if (reco!= null) {
        return ResponseEntity.ok(reco);
       }return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/update/{rec_id}")
    public ResponseEntity<Recordatory> updateRec(@RequestBody Recordatory rec, @PathVariable("rec_id") int rec_id){
        Recordatory reco = recordatoryService.updateRecordatory(rec, rec_id);
        if (reco!= null) {
            return ResponseEntity.ok(reco);
           }return ResponseEntity.badRequest().build();
    }

    @GetMapping("/rec/{rec_id}")
    public ResponseEntity<Recordatory> getRec(@PathVariable("rec_id") int rec_id){
        Recordatory reco = recordatoryService.getRec(rec_id);
        if (reco!= null) {
            return ResponseEntity.ok(reco);
           }return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/del/{rec_id}")
    public ResponseEntity<Recordatory> deleteRec(@PathVariable("rec_id") int rec_id){
        Recordatory reco = recordatoryService.deleteRec(rec_id);
        if (reco!= null) {
            return ResponseEntity.ok(reco);
           }return ResponseEntity.badRequest().build();
    }
}
