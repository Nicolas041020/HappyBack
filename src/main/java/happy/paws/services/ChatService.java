package happy.paws.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.Chat;
import happy.paws.model.Mensaje;
import happy.paws.model.Paseador;
import happy.paws.model.User;
import happy.paws.repositories.ChatRepository;
import happy.paws.repositories.MensajeRepository;
import happy.paws.repositories.PaseadorRepository;
import happy.paws.repositories.UserRepository;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    private MensajeRepository mensajeRepository;

    private UserRepository userRepository;

    private PaseadorRepository paseadorRepository;


    

    public ChatService(ChatRepository chatRepository, MensajeRepository mensajeRepository,
            UserRepository userRepository, PaseadorRepository paseadorRepository) {
        this.chatRepository = chatRepository;
        this.mensajeRepository = mensajeRepository;
        this.userRepository = userRepository;
        this.paseadorRepository = paseadorRepository;
    }

    public Chat obtenerOcrearChat(int usuarioId, int paseadorId) {
        User usuario = userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Paseador paseador = paseadorRepository.findById(paseadorId)
                .orElseThrow(() -> new RuntimeException("Paseador no encontrado"));

        return chatRepository.findByUserAndPaseador(usuario, paseador)
                .orElseGet(() -> {
                    Chat nuevoChat = new Chat();
                    nuevoChat.setUser(usuario);
                    nuevoChat.setPaseador(paseador);
                    return chatRepository.save(nuevoChat);
                });
    }

    public Mensaje enviarMsg(int usuarioId, int paseadorId, String content, boolean esUser){
        Chat chat = obtenerOcrearChat(usuarioId, paseadorId);
        User usuario = chat.getUser();
        Paseador paseador = chat.getPaseador();
        Mensaje msg = new Mensaje();
        msg.setContent(content);
        msg.setTimest(LocalDateTime.now());
        if (esUser) {
            msg.setRemitenteUsuario(usuario);
            msg.setDestinatarioPaseador(paseador);
        }else{
            msg.setRemitentePaseador(paseador);
            msg.setDestinatarioUsuario(usuario);
        }
        
        return mensajeRepository.save(msg);
    }

    public List<Mensaje> obtenerMsgChat(int chatId){
        return mensajeRepository.findByChatIdOrderByTimestAsc(chatId);
    }
}
