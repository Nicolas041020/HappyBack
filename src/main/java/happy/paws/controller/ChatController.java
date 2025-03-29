package happy.paws.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Mensaje enviarMensaje(MensajeDTO mensajeDTO) {
        System.out.println("MSG" + mensajeDTO.getContent());
        Mensaje mensaje = chatService.enviarMsg(
                mensajeDTO.getUsuarioId(),
                mensajeDTO.getPaseadorId(),
                mensajeDTO.getContent(),
                mensajeDTO.isEsDeUsuario()
        );

    messagingTemplate.convertAndSend("/chat/" + mensaje.getChat().getId(), mensaje);
    return mensaje;
    }

    @GetMapping("/{chatId}/mensajes")
    public List<Mensaje> obtenerMensajes(@PathVariable int chatId) {
        return chatService.obtenerMsgChat(chatId);
    }
}
