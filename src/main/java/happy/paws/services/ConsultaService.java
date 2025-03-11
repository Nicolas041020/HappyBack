package happy.paws.services;

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
}
