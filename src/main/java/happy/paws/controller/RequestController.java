package happy.paws.controller;

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

import happy.paws.model.Paseador;
import happy.paws.model.Request;
import happy.paws.model.User;
import happy.paws.services.RequestService;

@RestController
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/create/{user_id}/{paseador_id}")
    public ResponseEntity<Request> crearReq(@RequestBody Request request, @PathVariable("user_id") int user_id,@PathVariable("paseador_id") int paseador_id) throws Exception{
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

    @GetMapping("/msgP/{pas_id}")
    public ResponseEntity<Integer> pendingmsg(@PathVariable("pas_id") int pas_id){
        int num = requestService.pendmsg(pas_id);
        return ResponseEntity.ok(num);
    }

    @GetMapping("/requestPending/{pas_id}")
    public ResponseEntity<List<Request>> pendingRequest(@PathVariable("pas_id") int pas_id){
        List<Request> list = requestService.pendRequest(pas_id);
        if (list!=null) return ResponseEntity.ok(list);
        return null;
    }

    @PatchMapping("/edit/{req_id}/{code}")
    public ResponseEntity<Request> edit(@PathVariable("req_id") int req_id,@PathVariable("code") int code){
        Request req = requestService.editReq(req_id, code);
        if (req!= null) {
            return ResponseEntity.ok(req);
        }return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getRequests/{userId}")
    public ResponseEntity<List<Request>> getRequestUser(@PathVariable("userId") int userId){
        List<Request> lis = requestService.requestOfUser(userId);
        if (lis!=null) {
            return ResponseEntity.ok(lis);
        }return ResponseEntity.badRequest().build();
    }


    @GetMapping("/accept/{pas_id}")
    public ResponseEntity<List<User>> getUserAccepted(@PathVariable("pas_id") int pas_id){
        List<User> lis = requestService.getUsersAcceptedByPas(pas_id);
        if (lis!=null) {
            return ResponseEntity.ok(lis);
        }return null;
    }
    // filtrado por secuencia de caracteres
    @GetMapping("filter/{name}")
    public ResponseEntity<List<Paseador>> getPasLike(@PathVariable("name") String name){
        List<Paseador> lst = requestService.getPaseLike(name);
        if (lst!=null) {
            return ResponseEntity.ok(lst);
        }return ResponseEntity.badRequest().build();
    }

    //use este para borrar el request despues de q pase 1 min
    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteReq(@PathVariable("id") int id){
        Request req = requestService.delete(id);
        if (req!=null) {
            return ResponseEntity.ok().build();
        }return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getRequest/{req_id}")
    public ResponseEntity<Request> getRequest(@PathVariable("req_id") int req_id){
        Request req = requestService.getRequest(req_id);
        if (req!=null) return ResponseEntity.ok(req);
        return null;
    }


}
