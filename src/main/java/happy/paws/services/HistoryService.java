package happy.paws.services;

import org.springframework.stereotype.Service;

import happy.paws.model.History;
import happy.paws.model.Pet;
import happy.paws.repositories.HistoryRepository;
import happy.paws.repositories.PetRepository;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;
    private PetRepository petRepository;

    

    public HistoryService(HistoryRepository historyRepository, PetRepository petRepository) {
        this.historyRepository = historyRepository;
        this.petRepository = petRepository;
    }



    public History addHistory(History history,int pet_id){
        Pet pet = petRepository.findById(pet_id).orElse(null);
        history.setPet(pet);
       return historyRepository.save(history);
    }

}
