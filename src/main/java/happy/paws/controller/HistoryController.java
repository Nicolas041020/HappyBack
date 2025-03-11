package happy.paws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    
}
