package happy.paws.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import happy.paws.model.Paseador;
import happy.paws.model.Request;
import happy.paws.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create/{user_id}/{paseador_id}")
    public ResponseEntity<Request> crearReq(@RequestBody Request request, @PathVariable("user_id") int user_id,@PathVariable("paseador_id") int paseador_id){
        Request req = requestService.crear(request, user_id,paseador_id);
        if (req!=null) return ResponseEntity.ok(req);
        return null;
    }    

    @GetMapping("/getPas/{user_id}")
    public ResponseEntity<List<Paseador>> getPasPorUserId(@PathVariable("user_id") int user_id){
        List<Paseador> list = requestService.getPasPorUserId(user_id);
        if (list!=null) return ResponseEntity.ok(list);
        return null;

    }

    @GetMapping("/getRech/{user_id}")
    public ResponseEntity<List<Paseador>> getNoReloRech(@PathVariable("user_id") int user_id){
        List<Paseador> list = requestService.getNoRelRech(user_id);
        if (list!=null) return ResponseEntity.ok(list);
        return null;
    }
}
