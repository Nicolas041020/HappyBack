package happy.paws.services;

import org.springframework.stereotype.Service;

import happy.paws.model.Vet;
import happy.paws.repositories.VetRepository;

@Service
public class VetService {

    private VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public Vet addVet(Vet vet){
        return vetRepository.save(vet);
    }

    public Vet logIn(String ide, String passw){
       Vet vet = vetRepository.findByIdentification(ide);
       if (vet!=null && vet.getPassw().equals(passw)) return vet;
        return null;
    }

}
