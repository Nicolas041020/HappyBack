package happy.paws.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PASEADOR")
public class Paseador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String phoneNum;

    private String passw;
    @JsonIgnore
    @OneToMany(mappedBy = "paseador")
    List<Chat> chats = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "remitentePaseador")
    private List<Mensaje> mensajesEnviados = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "destinatarioPaseador")
    private List<Mensaje> mensajesRecibidos =  new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "paseador", cascade = CascadeType.ALL)
    private List<Request> requests;

    

    public Paseador(String name, String email, String phoneNum, String passw) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.passw = passw;
    }

    public Paseador(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }

    public List<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
        this.mensajesRecibidos = mensajesRecibidos;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    
    
}
