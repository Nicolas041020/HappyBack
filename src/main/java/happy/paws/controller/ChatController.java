package happy.paws.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import happy.paws.DTOs.MensajeDTO;
import happy.paws.model.Mensaje;
import happy.paws.services.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
    
    private ChatService chatService;

    private final SimpMessagingTemplate messagingTemplate;

   

    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/gchat/{userId}/{pasId}")
    public int getchatId(@PathVariable("userId") int userId,@PathVariable("pasId") int pasId){
        return chatService.obtenerOcrearChat(userId,pasId).getId();
    }

   // @MessageMapping("/chat")
    //@SendTo("/topic/messages")
   // public Mensaje enviarMensaje(MensajeDTO mensajeDTO) throws JsonProcessingException {
    //    System.out.println("MSG" + mensajeDTO.getContent());
   //     Mensaje mensaje = chatService.enviarMsg(
   //             mensajeDTO.getUsuarioId(),
   //             mensajeDTO.getPaseadorId(),
   //             mensajeDTO.getContent(),
   //             mensajeDTO.isEsDeUsuario()
   //     ); 

   // System.out.println("🟢 Enviando mensaje a WebSocket: " + new ObjectMapper().writeValueAsString(mensaje));
    //messagingTemplate.convertAndSend("/chat/" + mensaje.getChat().getId(), mensaje);
    //System.out.println("🔹 Mensaje enviado a WebSocket: " + mensaje.getContent());
    //return mensaje;
    //}

    @MessageMapping("/chat")
    public void enviarMensaje(MensajeDTO mensajeDTO) throws JsonProcessingException {
    //System.out.println("MSG " + mensajeDTO.getContent());
    Mensaje mensaje = chatService.enviarMsg(
            mensajeDTO.getUsuarioId(),
            mensajeDTO.getPaseadorId(),
            mensajeDTO.getContent(),
            mensajeDTO.isEsDeUsuario()
    );

    // Crear una instancia de MensajeDTO con solo los datos necesarios
    MensajeDTO mensajeResponse = new MensajeDTO();
    mensajeResponse.setContent(mensaje.getContent());

    System.out.println("🟢 Enviando mensaje a WebSocket: " + new ObjectMapper().writeValueAsString(mensajeResponse));
   // messagingTemplate.convertAndSend("/chat/4", "{ \"usuarioId\": 1, \"paseadorId\": 1, \"content\": \"Prueba\", \"esDeUsuario\": true }");

    messagingTemplate.convertAndSend("/topic/chat/" + mensaje.getChat().getId(), mensajeResponse);
}


    @GetMapping("/{chatId}/mensajes")
    public List<Mensaje> obtenerMensajes(@PathVariable int chatId) {
        return chatService.obtenerMsgChat(chatId);
    }
}