package happy.paws.services;

import java.sql.Date;
import java.util.List;

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

    public List<History> getAllHistories(){
        return historyRepository.findAll();
    }

    public List<History> getHistoryByPetId(int petId){
        return historyRepository.findAllByPetId(petId);
    }

    public History updateHistory(History history, int id){
        History histo = historyRepository.findById(id).orElse(null);
        if (histo==null) return null;

        
        if (history.getDate()!= null) histo.setDate(history.getDate());
        if (!history.getVaccine().isEmpty()) histo.setVaccine(history.getVaccine());
        if (history.getDosis()!= 0) histo.setDosis(history.getDosis());
        if (history.getCuantity()!= 0) histo.setCuantity(history.getCuantity());
        if (!history.getComments().isEmpty()) histo.setComments(history.getComments());
        if (!history.getReason().isEmpty()) histo.setReason(history.getReason());

        historyRepository.save(histo);
        return histo; 

    }

    public History deleteHistory(int id){
        History his = historyRepository.findById(id).orElse(null);
        historyRepository.delete(his);
        return his;
    }

    public List<History> findVaccinesB(Date fecha1,Date fecha2){
        return historyRepository.findVaccinesBetween(fecha1,fecha2);
    }

    public int getNumVaccines(String vaccine){
        return historyRepository.countByVaccine(vaccine);
    }
}
