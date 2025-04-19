package happy.paws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.Consulta;
import happy.paws.model.Pet;
import happy.paws.repositories.ConsultaRepository;
import happy.paws.repositories.PetRepository;

@Service
public class ConsultaService {

    private ConsultaRepository consultaRepository;
    private PetRepository petRepository;

    
    

    public ConsultaService(ConsultaRepository consultaRepository, PetRepository petRepository) {
        this.consultaRepository = consultaRepository;
        this.petRepository = petRepository;
    }

    public Consulta addConsulta(Consulta consulta, int pet_id){
        Pet pet = petRepository.findById(pet_id).orElse(null);
        pet.getConsultas().add(consulta);
        consulta.setPet(pet);
        petRepository.save(pet);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> getConsultas(int pet_id){
        return consultaRepository.getConsultasByPetId(pet_id);
    }

    public List<Consulta> getCons(){
        return consultaRepository.findAll();
    }

    public Consulta getConsultaById(int id){
        return consultaRepository.findById(id).orElse(null);
    }

    //public List<Consulta> verConsultas(int user_id){
    //    return consultaRepository.findAllByUserId(user_id);
    //}

    public Consulta updateConsulta(Consulta consulta, int id){
        Consulta con = consultaRepository.findById(id).orElse(null);
        if (con == null) {
            return null;
        }
        if(consulta.getFecha()!=null) con.setFecha(consulta.getFecha());
        if(!consulta.getMotivo().isEmpty()) con.setMotivo(consulta.getMotivo());
        if(!consulta.getEstado().isEmpty()) con.setEstado(consulta.getEstado());
        if(!consulta.getVeterinario().isEmpty()) con.setVeterinario(consulta.getVeterinario());
        if(!consulta.getResultado().isEmpty()) con.setResultado(consulta.getResultado());

        consultaRepository.save(con);
        return con;
    }

    public Consulta deleteConsulta(int id){
        Consulta con = consultaRepository.findById(id).orElse(null);
        consultaRepository.delete(con);
        return con;
    }

    public Pet mascotaFrec(){
        return consultaRepository.getMascotaMasFreqC();
    }

    public List<Pet> state(String state){
        return consultaRepository.findByState(state);
    }

    public int countVisitsById(int pet_id){
        return consultaRepository.countVisitasByPetId(pet_id);
    }
}
