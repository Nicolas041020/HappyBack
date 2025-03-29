package happy.paws.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
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
         LocalDate localDate = recordatory.getDate().toLocalDate().plusDays(1);
        recordatory.setDate(java.sql.Date.valueOf(localDate));
        Recordatory rec = recordatoryService.add(recordatory, pet_id);
        if(rec!=null) return ResponseEntity.ok(rec);
        return null;
    }
}
