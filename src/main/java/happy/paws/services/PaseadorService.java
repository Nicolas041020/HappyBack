package happy.paws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.Paseador;
import happy.paws.repositories.PaseadorRepository;

@Service
public class PaseadorService {

    private PaseadorRepository paseadorRepository;

    public PaseadorService(PaseadorRepository paseadorRepository) {
        this.paseadorRepository = paseadorRepository;
    }

    public List<Paseador> getPaseadores(){
        List<Paseador> lista = paseadorRepository.findAll();
        return !lista.isEmpty() ? lista : null;
    }

    public Paseador savePaseador(Paseador paseador){
        return paseadorRepository.save(paseador);
    }

    public Paseador logIn(String email, String contrasena){
        Paseador paseador = paseadorRepository.findByEmail(email);
        if(paseador!=null && paseador.getPassw().equals(contrasena)){
            return paseador;
        }
        return null;
    }
}
