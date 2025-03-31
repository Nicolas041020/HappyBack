package happy.paws.model;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MENSAJE")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timest;

    @ManyToOne()
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "remitente_usuario_id", nullable = true)
    private User remitenteUsuario;

    @ManyToOne
    @JoinColumn(name = "remitente_paseador_id", nullable = true)
    private Paseador remitentePaseador;

    @ManyToOne
    @JoinColumn(name = "destinatario_usuario_id", nullable = true)
    private User destinatarioUsuario;

    @ManyToOne
    @JoinColumn(name = "destinatario_paseador_id", nullable = true)
    private Paseador destinatarioPaseador;


    public Mensaje(String content, LocalDateTime timest) {
        this.content = content;
        this.timest = timest;
    }
    public Mensaje(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimest() {
        return timest;
    }

    public void setTimest(LocalDateTime timest) {
        this.timest = timest;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
    public User getRemitenteUsuario() {
        return remitenteUsuario;
    }
    public void setRemitenteUsuario(User remitenteUsuario) {
        this.remitenteUsuario = remitenteUsuario;
    }
    public Paseador getRemitentePaseador() {
        return remitentePaseador;
    }
    public void setRemitentePaseador(Paseador remitentePaseador) {
        this.remitentePaseador = remitentePaseador;
    }
    public User getDestinatarioUsuario() {
        return destinatarioUsuario;
    }
    public void setDestinatarioUsuario(User destinatarioUsuario) {
        this.destinatarioUsuario = destinatarioUsuario;
    }
    public Paseador getDestinatarioPaseador() {
        return destinatarioPaseador;
    }
    public void setDestinatarioPaseador(Paseador destinatarioPaseador) {
        this.destinatarioPaseador = destinatarioPaseador;
    }

    

    

}
