package happy.paws.DTOs;

import lombok.Data;

@Data
public class MensajeDTO {

    private int usuarioId;
    private int paseadorId;
    private String content;
    private boolean esDeUsuario;

}
