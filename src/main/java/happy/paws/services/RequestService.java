package happy.paws.services;

import java.net.PasswordAuthentication;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import happy.paws.model.Paseador;
import happy.paws.model.Request;
import happy.paws.model.User;
import happy.paws.repositories.PaseadorRepository;
import happy.paws.repositories.RequestRepository;
import happy.paws.repositories.UserRepository;
import jakarta.mail.MessagingException;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    private PaseadorRepository paseadorRepository;

    private UserRepository userRepository;

    private EmailService emailService;

    

    public RequestService(RequestRepository requestRepository, PaseadorRepository paseadorRepository,
            UserRepository userRepository, EmailService emailService) {
        this.requestRepository = requestRepository;
        this.paseadorRepository = paseadorRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }



    public Request crear(Request request, int user_id,int paseador_id) throws Exception{

        Paseador pas = paseadorRepository.findById(user_id).orElse(null);
        User us = userRepository.findById(paseador_id).orElse(null);
        if (pas != null && us != null) {
            request.setDate(LocalDateTime.now());
            request.setPaseador(pas);
            request.setUsuario(us);
          String destinatario = pas.getEmail();
            String asunto = "Solicitud de Paseo de :" + us.getFirstname();
            String cuerpo = "<h1>¡Tienes una nueva solicitud!</h1>" +
                         "<p><strong>" + request.getContenido() + "Solicitud creada el :" + request.getDate() + ".</strong></p>";
            try {
            emailService.sendEmail(destinatario, asunto, cuerpo);
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
            return requestRepository.save(request);
        }else{
            return null;
        }
         
    }

    public List<Paseador> getPasPorUserId(int user_id){
        List<Paseador> lista = requestRepository.getPaseadoresPorUserId(user_id);
        if (lista != null) {
            return lista;
        }
        return null;
    }

    public List<Paseador> getNoRelRech(int userId){
      //  List<Integer> lis = requestRepository.findPaseadoresRechazadosByUsuario(user_id);

       // ArrayList<Paseador> lista = new ArrayList<>();
        //for (int index = 0; index < lis.size(); index++) {
          //  lista.add(paseadorRepository.findById(lis.get(index)).orElse(null));
        //}
        //List<Integer> array = requestRepository.findNoRela(user_id);
        //List<Paseador> arr = new ArrayList<>();
        //for(Integer ele : array){
        //    arr.add(paseadorRepository.findById(ele).orElse(null));
        //}
        
        //for(Paseador el : arr){
          //  lista.add(el);
        //}

        //if (lista!= null) {
          //  return lista;
        //}
        //return null;
          // 1) IDs rechazados

      List<Integer> rechazados = requestRepository.findPaseadoresRechazadosByUsuario(userId);
      // 2) IDs sin relación
      List<Integer> sinRelacion = requestRepository.findNoRela(userId);

      // 3) Unir ambas listas y eliminar duplicados
      List<Integer> todosLosIds = Stream
          .concat(sinRelacion.stream(), rechazados.stream())
          //.concat(rechazados.stream(), sinRelacion.stream())
          .distinct()
          .collect(Collectors.toList());

      // 4) Recuperar en batch todos los paseadores cuyas PK estén en todosLosIds
      return paseadorRepository.findAllById(todosLosIds);
    }

    public Integer pendmsg(int pas_id){
      return requestRepository.findNumNotAccepted(pas_id);
    }

    public List<Request> pendRequest(int pas_id){
      return requestRepository.findRequestToAccept(pas_id);
    }

    public Request editReq(int req_id, int editNum){
      Request req = requestRepository.findById(req_id).orElse(null);
      if (editNum == -1 || editNum == 0 || editNum == 1) {
        req.setEstado(editNum);
        return requestRepository.save(req);
        }
      return null;
    }

    public List<Request> requestOfUser(int userId){
      return requestRepository.findRequestOfUser(userId);
    }

    public List<User> getUsersAcceptedByPas(int pas_id){
      return requestRepository.getPaseadoresAceptadosUser(pas_id);
    }

    public List<Paseador> getPaseLike(String name){
      return requestRepository.findPaseadoresLikee(name);
    }

    public Request delete(int id){
      Request req = requestRepository.findById(id).orElse(null);
      requestRepository.delete(req);
      return req;
    }
}
