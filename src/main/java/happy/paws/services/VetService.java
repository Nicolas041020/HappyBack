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

    public Vet logIn(String email, String id){
       Vet vet = vetRepository.getbyEmail(email);
       if (vet!=null && vet.getIdentification().equals(id)) return vet;
        return null;
    }

}
