package happy.paws.DTOs;

import lombok.Data;

@Data
public class MensajeDTO {

    private int usuarioId;
    private int paseadorId;
    private String content;
    private boolean esDeUsuario;

    public MensajeDTO(){

    }

    public int getUsuarioId(){
        return usuarioId;
    }

    public int getPaseadorId(){
        return paseadorId;
    }

    public String getContent(){
        return content;
    }

    public boolean isEsDeUsuario(){
        return esDeUsuario;
    }

    public void setUsuarioId(int usuarioId){
        this.usuarioId = usuarioId;
    }

    public void setPaseadorId(int paseadorId){
        this.paseadorId = paseadorId;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setEsDeUsuario(boolean esDeUsuario){
        this.esDeUsuario = esDeUsuario;
    }

}
